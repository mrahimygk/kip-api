package routing

import application.redirect
import application.securityCode
import db.dao.KweetDao
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import routing.routeutil.Login
import routing.routeutil.NewKtweet
import session.KweetSession

fun Routing.ktweet(userDao: UserDao, kweetDao: KweetDao) {

    get<NewKtweet> {
        val user = call.sessions.get<KweetSession>()?.let {
            userDao.getUser(it.userID, null)
        }

        if (user == null) {
            call.redirect(Login())
        } else {
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user)

            call.respond(
                FreeMarkerContent(
                    "new_ktweet.ftl",
                    mapOf(
                        "user" to user,
                        "date" to date,
                        "code" to code
                    ), user.userID
                )
            )
        }
    }
}