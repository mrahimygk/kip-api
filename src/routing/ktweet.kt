package routing

import db.dao.NoteDao
import db.dao.UserDao
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.routing.Routing
import routing.routeutil.NewKtweet
import routing.routeutil.ViewKtweet

fun Routing.ktweet(userDao: UserDao, noteDao: NoteDao) {

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