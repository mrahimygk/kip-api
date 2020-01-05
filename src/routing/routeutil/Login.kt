package routing.routeutil

import io.ktor.locations.Location


@Location("/register")
data class Register(val userID: String = "", val name: String = "", val email: String = "", val error: String = "")

@Location("/login")
data class Login(val userID: String = "", val error: String = "")

@Location("/logout")
class Logout()