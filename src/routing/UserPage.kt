package routing

import db.dao.note.NoteDao
import db.dao.user.UserDao
import io.ktor.locations.get
import io.ktor.routing.Route
import routing.routeutil.UserPage

fun Route.userPage(userDao: UserDao, noteDao: NoteDao) {

    get<UserPage> {

    }
}