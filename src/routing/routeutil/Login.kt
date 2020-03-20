package routing.routeutil

import io.ktor.locations.Location


@Location("/register")
data class Register(val email: String = "", val error: String = "")

@Location("/login")
data class Login(val email: String = "", val error: String = "")

@Location("/logout")
class Logout()