package routing

import application.redirect
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.sessions.*
import pojo.User
import routing.routes.Register
import routing.routes.UserPage
import session.KweetSession

fun Routing.register(userDao: UserDao) {
    post<Register> {
        val user = call.sessions.get<KweetSession>()?.let {
            userDao.getUser(it.userID)
        }
        if (user != null) return@post call.redirect(UserPage(user.userID))

        val registration = call.receive<Parameters>()
        val id = registration["userId"] ?: return@post call.redirect(it)
        val pass = registration["pass"] ?: return@post call.redirect(it)
        val name = registration["name"] ?: return@post call.redirect(it)
        val email = registration["email"] ?: return@post call.redirect(it)

        val error = Register(id, name, email)

        when {
            pass.length < 6 -> call.redirect(error.copy(error = "Password is shit"))
            else->{}
        }
        call.respondText(userDao.createUser(User(id, email, name, pass)).toString())
    }

    get<Register> {
        call.respond(
            FreeMarkerContent(
                "register.ftl",
                mapOf(
                    "pageUser" to User(it.userId, it.email, it.displayName, ""),
                    "error" to it.error
                )
            )
        )
    }
}