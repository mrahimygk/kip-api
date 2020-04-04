package db.entity

import org.jetbrains.exposed.sql.Table

object NoteModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val userEmail = varchar("user_email", 128).index()
    val title = varchar("title", 1024)
    val content = varchar("content", 2048)
    val color = varchar("color", 10)

    /**
     * ref [DrawingModel]
     */
    //val drawingList = varchar("drawingList", 1024)

    /**
     * ref [VoiceModel]
     */
    //val voiceList = varchar("voiceList", 1024)

    /**
     * ref [LabelJoinNoteModel]
     */
    //val labelList = varchar("labelList", 1024)

    /**
     * ref [CheckboxModel]
     */
    //val checkboxList = varchar("checkboxList", 1024)

    //    val replyTo = integer("reply_to").index().nullable()
//    val directReplyTo = integer("direct_reply_to").index().nullable()
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object DrawingModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val path = varchar("path", 1024)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object VoiceModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val path = varchar("path", 1024)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object LabelJoinNoteModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val labelId = integer("label_id").index()
    val noteId = varchar("note_id", 128).index()
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object LabelModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val text = varchar("text", 128)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object CheckboxModel : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val text = varchar("text", 128)
    val indent = integer("indent")
    val checked = bool("checked")
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}