package application

import com.mchange.v2.c3p0.ComboPooledDataSource
import db.dao.KweetDaoImpl
import db.dao.UserDaoImpl
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.ApplicationStopped
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
import org.h2.Driver
import org.jetbrains.exposed.sql.Database
import routing.hello
import routing.root
import routing.styles
import session.KweetSession
import java.io.File

val dir = File("build/db")

val pool = ComboPooledDataSource().apply {
    driverClass = Driver::class.java.name
    jdbcUrl = "jdbc:h2:file:${dir.canonicalFile.absolutePath}"
    user = ""
    password = ""
}

val db = Database.connect(pool)
val userDao = UserDaoImpl(db)
val kweetDao = KweetDaoImpl(db)

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    userDao.init()
    kweetDao.init()
    environment.monitor.subscribe(ApplicationStopped) { pool.close() }
    dependencies()
    routing()
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

private fun Application.routing() {
    routing {
        hello()
        root(kweetDao, userDao)
        styles()
    }
}