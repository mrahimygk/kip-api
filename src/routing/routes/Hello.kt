package routing.routes

import io.ktor.locations.Location

const val helloLocationRoute = "/hello"
@Location(helloLocationRoute)
class Hello