package routing

import db.dao.KweetDaoImpl
import db.dao.UserDaoImpl
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import routing.routes.Root
import session.KweetSession

fun Routing.root(kweetDao: KweetDaoImpl, userDao: UserDaoImpl) {
    get<Root> {
        val user = call.sessions.get<KweetSession>()?.let {
            userDao.getUser(it.userID)
        }

        val top = kweetDao.top(10).map { kweetDao.getKweet(it) }
        val latest = kweetDao.latest(10).map { kweetDao.getKweet(it) }

        call.respondText(user.toString())
//        call.respond(FreeMarkerContent())
    }
//
//    get("/html") {
//        call.respondHtml {
//            head {
//                title { +"Ktor juju" }
//            }
//
//            body {
//                p {
//                    +"hello"
//                }
//            }
//        }
//    }
}