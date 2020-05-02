package routing

import db.dao.note.NoteDaoImpl
import db.dao.user.UserDaoImpl
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Routing
import routing.routeutil.Root

fun Routing.root(kweetDao: NoteDaoImpl, userDao: UserDaoImpl) {
    get<Root> {
        call.respond(TextContent("""{"message":"hello world"}""", ContentType.Application.Json))
    }
}