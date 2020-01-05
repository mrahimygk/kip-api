package routing

import db.dao.KweetDaoImpl
import db.dao.UserDaoImpl
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import routing.routeutil.Root
import session.KweetSession

fun Routing.root(kweetDao: KweetDaoImpl, userDao: UserDaoImpl) {
    get<Root> {
        val user = call.sessions.get<KweetSession>()?.let {
            userDao.getUser(it.userID)
        }

        val top = kweetDao.top(10).map { kweetDao.getKweet(it) }
        val latest = kweetDao.latest(10).map { kweetDao.getKweet(it) }

        val etagString = user?.userID + "," + top.joinToString { it.id.toString() } + latest.joinToString { it.id.toString() }
        val etag = etagString.hashCode()

        call.respond(
            FreeMarkerContent(
                "index.ftl",
                mapOf("top" to top, "latest" to latest, "user" to user),
                etag.toString()
            )
        )
    }
}