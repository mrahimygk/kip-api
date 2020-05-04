package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class DrawingModel(
    val id: String,
    val path: String,
    val created: String,
    val modified: String
) : Serializable