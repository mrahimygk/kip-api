package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class LabelJoinNoteModel(
    val id: String,
    val noteId: String,
    val labelId: String,
    val createdDate: DateTime,
    val modifiedDate: DateTime
) : Serializable