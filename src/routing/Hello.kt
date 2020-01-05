package routing

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Routing
import routing.routeutil.Hello

fun Routing.hello(){
    get<Hello>{
        call.respondText("HELLO WORLD")
    }
}