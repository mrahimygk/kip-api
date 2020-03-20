package pojo

import java.io.Serializable

data class User(
    val email: String,
    val avatar: String,
    val hash: String
) : Serializable