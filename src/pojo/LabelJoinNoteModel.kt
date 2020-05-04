package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class LabelJoinNoteModel(
    val id: String,
    val noteId: String,
    val labelId: String,
    val created: String,
    val modified: String
) : Serializable