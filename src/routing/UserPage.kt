package routing

import db.dao.KweetDao
import db.dao.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import routing.routes.UserPage
import session.KweetSession

fun Route.userPage(userDao: UserDao, kweetDao: KweetDao) {

    get<UserPage> {
        val user = call.sessions.get<KweetSession>()?.let { ks -> userDao.getUser(ks.userID, null) }
        val pageUser = userDao.getUser(it.user, null)

        if (pageUser == null) {
            call.respond(HttpStatusCode.NotFound.description("User ${it.user} is not here :("))
        } else {
            val kweets = kweetDao.userKweets(it.user).map { kId -> kweetDao.getKweet(kId) }
            val etag = (user?.userID ?: "") + "_" + kweets.map {
                it.content.hashCode()
            }.hashCode().toString()

            call.respond(
                FreeMarkerContent(
                    "user.ftl",
                    mapOf("user" to user, "pageUser" to pageUser, "kweets" to kweets),
                    etag
                )
            )
        }
    }
}