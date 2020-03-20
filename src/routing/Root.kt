package routing

import application.redirect
import db.dao.KweetDaoImpl
import db.dao.UserDaoImpl
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.content.TextContent
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.request.receiveOrNull
import io.ktor.response.respond
import io.ktor.routing.Routing
import routing.routeutil.Root

fun Routing.root(kweetDao: KweetDaoImpl, userDao: UserDaoImpl) {
    get<Root> {
        call.respond(TextContent("""{"message":"hello world"}""", ContentType.Application.Json))
    }
}