package routing

import application.redirect
import com.google.gson.Gson
import crypto.hash
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.content.TextContent
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.clear
import io.ktor.sessions.sessions
import ktx.isValidEmail
import routing.routeutil.Login
import routing.routeutil.Logout
import routing.routeutil.Root
import session.KweetSession

fun Routing.login(userDao: UserDao) {
    post<Login> {
        val post = call.receive<Parameters>()
        val email = post["email"] ?: return@post call.redirect(it)
        val password = post["pass"] ?: return@post call.redirect(it)

        val error = Login(email)

        val login = when {
            !email.isValidEmail() -> null
            password.length < 6 -> null
            else -> userDao.getUser(email, hash(password))
        }

        if (login == null) call.redirect(error.copy(error = "Invalid login"))
        else call.respond(TextContent(Gson().toJson(login), ContentType.Application.Json))

    }

    get<Logout> {
        call.sessions.clear<KweetSession>()
        call.redirect(Root())
    }
}