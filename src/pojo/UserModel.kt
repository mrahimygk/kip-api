package pojo

import java.io.Serializable

data class UserModel(
    val email: String,
    val avatar: String,
    val hash: String
) : Serializable