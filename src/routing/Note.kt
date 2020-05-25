package routing

import db.dao.note.NoteDao
import db.dao.user.UserDao
import io.ktor.application.call
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.contentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import pojo.NoteModel
import routing.routeutil.NewNote
import routing.routeutil.ViewNote

fun Routing.note(userDao: UserDao, noteDao: NoteDao) {

    post<NewNote> {
//        call.respond(TextContent("""{"TODO":"add new note , get user id"}""", ContentType.Application.Json))
        val note = call.receive<NoteModel>()
//        call.respond(TextContent("""{"TODO":"add new note , ${post["date"]}"}""", ContentType.Application.Json))
//        call.respond(TextContent(note.toString(), ContentType.Application.Json))

        noteDao.insert(note)

//        noteDao.insert(
//            NoteModel(
//                post["id"].toString(),
//                post["email"].toString(),
//                post["title"].toString(),
//                post["content"].toString(),
//                post["color"].toString(),
//                emptyList(),
//                emptyList(),
//                emptyList(),
//                emptyList(),
//                post["is_pinned"]?.toBoolean() ?: false,
//                DateTime(post["created_date"].toString()),
//                DateTime(post["modified_date"].toString())
//            )
//        )
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

    get<ViewNote> {
        call.respond(TextContent(noteDao.get(it.id.toString()).toString(), ContentType.Application.Json))
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