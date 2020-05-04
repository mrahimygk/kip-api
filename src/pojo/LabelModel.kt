package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class LabelModel(
    val id: String,
    val text: String,
    val created: String,
    val modified: String
) : Serializable