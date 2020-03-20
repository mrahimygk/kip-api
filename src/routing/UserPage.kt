package routing

import db.dao.KweetDao
import db.dao.UserDao
import io.ktor.locations.get
import io.ktor.routing.Route
import routing.routeutil.UserPage

fun Route.userPage(userDao: UserDao, kweetDao: KweetDao) {

    get<UserPage> {

    }
}