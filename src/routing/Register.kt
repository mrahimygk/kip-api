package routing

import application.redirect
import crypto.hash
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.*
import ktx.isValidUserName
import pojo.User
import routing.routes.Register
import routing.routes.UserPage
import session.KweetSession

fun Routing.register(userDao: UserDao) {
    post<Register> {
        val currentUser = call.sessions.get<KweetSession>()?.let { ks ->
            userDao.getUser(ks.userID)
        }
        if (currentUser != null) return@post call.redirect(UserPage(currentUser.userID))

        val registration = call.receive<Parameters>()
        val id = registration["userID"] ?: return@post call.redirect(it)
        val pass = registration["hash"] ?: return@post call.redirect(it)
        val name = registration["name"] ?: return@post call.redirect(it)
        val email = registration["email"] ?: return@post call.redirect(it)

        val registerValidation = Register(id, name, email)

        when {
            pass.length < 6 -> call.redirect(registerValidation.copy(error = "Password is shit"))
            id.length < 4 -> call.redirect(registerValidation.copy(error = "4 chars plz"))
            !id.isValidUserName() -> call.redirect(registerValidation.copy(error = "Not valid user name"))
            userDao.getUser(id) != null -> call.redirect(registerValidation.copy(error = "user exists"))
            userDao.getUser(email) != null -> call.redirect(registerValidation.copy(error = "email taken"))
            else -> {
                val hash = hash(pass)
                val newUser = User(id, email, name, hash)

                userDao.createUser(newUser)
                call.sessions.set(KweetSession(newUser.userID))
                call.redirect(UserPage(newUser.userID))
            }
        }
    }

    get<Register> {
        call.respond(
            FreeMarkerContent(
                "register.ftl",
                mapOf(
                    "pageUser" to User(it.userID, it.email, it.name, ""),
                    "error" to it.error
                )
            )
        )
    }
}