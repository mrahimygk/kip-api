package routing

import application.redirect
import db.dao.KweetDao
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import routing.routeutil.Login
import routing.routeutil.NewKtweet
import routing.routeutil.ViewKtweet
import session.KweetSession

fun Routing.ktweet(userDao: UserDao, kweetDao: KweetDao) {

    post<NewKtweet> {
//        val post = call.receive<Parameters>()
//        val date = post["date"]?.toLongOrNull() ?: return@post call.redirect(it)
//        val code = post["code"] ?: return@post call.redirect(it)
//        val text = post["text"] ?: return@post call.redirect(it)
//        val email = post["ownerEmail"] ?: return@post call.redirect(it)
//
//        if (user == null || !call.verifyCode(date, user, code)) call.redirect(Login())
//        else {
//            val id = kweetDao.createKweet(user.userID, text, null)
//            call.redirect(ViewKtweet(id))
//        }
    }

    get<ViewKtweet> {
//        val user = call.sessions.get<KweetSession>()?.let { userDao.getUser(it.userID, null) }
//        val date = System.currentTimeMillis()
//        val code = if (user != null) call.securityCode(date, user) else null
//
//        call.respond(
//            FreeMarkerContent(
//                "view_ktweet.ftl",
//                mapOf("user" to user, "ktweet" to kweetDao.getKweet(it.id), "date" to date, "code" to code),
//                user?.userID ?: ""
//            )
//        )
    }
}