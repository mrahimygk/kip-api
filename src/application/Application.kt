package application

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.freemarker.FreeMarker
import io.ktor.gson.gson
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.SessionTransportTransformerMessageAuthentication
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.util.hex
import routing.root
import routing.styles
import session.KweetSession

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    dependencies()

    routing {
        root()
        styles()
    }
}

private fun Application.dependencies() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ConditionalHeaders)
    install(PartialContent)
    install(Locations)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    install(Sessions) {
        cookie<KweetSession>("SESSION") {
            transform(SessionTransportTransformerMessageAuthentication(hex("4b8ca83")))
        }
    }

    install(ContentNegotiation) {
        gson {

        }
    }

    install(Routing) {

    }
}