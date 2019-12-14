package routing

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title


fun Routing.root(){
    get("/") {
        call.respondText("Hi gogoli")
    }

    get("/html") {
        call.respondHtml {
            head {
                title { +"Ktor juju" }
            }

            body {
                p {
                    +"hello"
                }
            }
        }
    }
}