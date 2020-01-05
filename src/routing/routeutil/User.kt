package routing.routeutil

import io.ktor.locations.Location


@Location("/user/{user}")
data class UserPage(val user: String)
