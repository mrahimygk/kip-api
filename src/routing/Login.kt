package routing

import api.reponse.ErrorCodes
import application.redirect
import crypto.hash
import db.dao.user.UserDao
import io.ktor.application.call
import io.ktor.http.Parameters
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.clear
import io.ktor.sessions.sessions
import ktx.isValidEmail
import pojo.ApiResponse
import pojo.copy
import pojo.toTextContent
import routing.routeutil.Login
import routing.routeutil.Logout
import routing.routeutil.Root
import session.KweetSession

fun Routing.login(userDao: UserDao) {
    post<Login> {
        val apiResponse = ApiResponse(ErrorCodes.SUCCESS)
        val post = call.receive<Parameters>()
        val email = post["email"]?.trim() ?: return@post call.respond(
            apiResponse.copy(ErrorCodes.EMPTY_EMAIL).toTextContent()
        )
        val password = post["pass"] ?: return@post call.respond(
            apiResponse.copy(ErrorCodes.EMPTY_PASSWORD).toTextContent()
        )

        val response = when {
            !email.isValidEmail() -> apiResponse.copy(ErrorCodes.INVALID_EMAIL)
            password.length < 6 -> apiResponse.copy(ErrorCodes.INVALID_PASSWORD)
            else -> {
                val data = userDao.getUser(email, hash(password))
                if (data == null)
                    apiResponse.copy(ErrorCodes.INVALID_LOGIN)
                else apiResponse.copy(data = data)
            }
        }
        call.respond(response.toTextContent())
    }

    get<Logout> {
        call.sessions.clear<KweetSession>()
        call.redirect(Root())
    }
}