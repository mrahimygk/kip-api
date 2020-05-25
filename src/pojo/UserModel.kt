package pojo

import io.ktor.auth.Principal
import java.io.Serializable

data class UserModel(
    val email: String,
    val avatar: String,
    val hash: String,
    val token : String?,
    val refreshToken : String?
) : Serializable, Principal