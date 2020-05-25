package routing

import db.dao.note.NoteDao
import db.dao.user.UserDao
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respondText
import io.ktor.routing.Route
import routing.routeutil.UserPage

fun Route.userPage(userDao: UserDao, noteDao: NoteDao) {

    get<UserPage> {
        call.respondText("AUTH TEST")
    }

    post<UserPage> {
        call.respondText("AUTH TEST")
    }
}