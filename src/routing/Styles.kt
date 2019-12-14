package routing

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

@Location("/styles/main.css")
class MainCss()
fun Routing.styles() {

    get<MainCss> {
        call.resolveResource("blog.css")?.let {
            call.respond(it)
        }
    }
}