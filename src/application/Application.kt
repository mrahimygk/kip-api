package application

import com.mchange.v2.c3p0.ComboPooledDataSource
import db.dao.note.*
import db.dao.user.UserDaoImpl
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.ApplicationStopped
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.jwt
import io.ktor.features.*
import io.ktor.freemarker.FreeMarker
import io.ktor.gson.gson
import io.ktor.locations.Locations
import io.ktor.locations.locations
import io.ktor.request.host
import io.ktor.request.port
import io.ktor.response.respondRedirect
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.h2.Driver
import org.jetbrains.exposed.sql.Database
import routing.*
import java.io.File
import java.text.DateFormat

val dir = File("build/db")

val pool = ComboPooledDataSource().apply {
    driverClass = Driver::class.java.name
    jdbcUrl = "jdbc:h2:file:${dir.canonicalFile.absolutePath}"
    user = ""
    password = ""
}

val db = Database.connect(pool)
val userDao = UserDaoImpl(db)
val labelDao = LabelDaoImpl(db)
val voiceDao = VoiceDaoImpl(db)
val drawingDao = DrawingDaoImpl(db)
val checkboxDao = CheckboxDaoImpl(db)
val labelJoinNoteDao = LabelJoinNoteDaoImpl(db)
val noteDao =
    NoteDaoImpl(labelDao, labelJoinNoteDao, drawingDao, voiceDao, checkboxDao, db)

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    userDao.init()
    noteDao.init()
    labelDao.init()
    voiceDao.init()
    drawingDao.init()
    checkboxDao.init()
    labelJoinNoteDao.init()
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
    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = JwtConfig.realm
            validate {
                it.payload.getClaim("email").asString()?.let(userDao::getUserByEmail)
            }
        }
    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "/templates")
    }

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    install(Routing) {

    }
}

private fun Application.routing() {
    routing {
        hello()
        root(noteDao, userDao)
        styles()
        register(userDao)
        login(userDao)
        authenticate {
            userPage(userDao, noteDao)
        }
        note(userDao, noteDao)
    }
}

suspend fun ApplicationCall.redirect(location: Any) {
    val host = request.host()
    val port = request.port().let { if (it == 80) "" else ":$it" }
    val address = "$host$port"

    respondRedirect("http://$address${application.locations.href(location)}")
}