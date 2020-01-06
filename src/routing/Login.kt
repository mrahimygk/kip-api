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
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import ktx.isValidUserName
import routing.routeutil.Login
import routing.routeutil.Logout
import routing.routeutil.Root
import routing.routeutil.UserPage
import session.KweetSession
import kotlin.math.log

fun Routing.login(userDao: UserDao) {
    get<Login> {
        val user = call.sessions.get<KweetSession>()?.let {
            userDao.getUser(it.userID, null)
        }

        if (user != null) call.redirect(UserPage(user.userID))
        else call.respond(
            FreeMarkerContent(
                "login.ftl"
                , mapOf("userId" to it.userID, "error" to it.error), ""
            )
        )
    }

    post<Login> {
        val post = call.receive<Parameters>()
        val userId = post["userId"] ?: return@post call.redirect(it)
        val password = post["password"] ?: return@post call.redirect(it)

        val error = Login(userId)

        val login = when {
            userId.length < 4 -> null
            password.length < 6 -> null
            !userId.isValidUserName() -> null
            else -> userDao.getUser(userId, hash(password))
        }

        if (login == null) call.redirect(error.copy(error = "رمز عبور یا نام کاربری نامعتبر است"))
        else {
            call.sessions.set(KweetSession(login.userID))
            call.redirect(UserPage(login.userID))
        }
    }

    get<Logout>{
        call.sessions.clear<KweetSession>()
        call.redirect(Root())
    }
}