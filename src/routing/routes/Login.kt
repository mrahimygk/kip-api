package routing.routes

import io.ktor.locations.Location


@Location("/register")
data class Register(val userId: String = "", val displayName: String = "", val email: String = "", val error: String = "")

@Location("/login")
data class Login(val userId: String = "", val error: String = "")

@Location("/logout")
class Logout()