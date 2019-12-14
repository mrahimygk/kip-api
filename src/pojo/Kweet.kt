package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class Kweet(
    val id: Int,
    val user: String,
    val content: String,
    val date: DateTime,
    val replyTo: Int?
) : Serializable