package db.entity

import org.jetbrains.exposed.sql.Table

object NoteEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val userEmail = varchar("user_email", 128).index()
    val title = varchar("title", 1024)
    val content = varchar("content", 2048)
    val color = varchar("color", 10)
    val isPinned = bool("is_pinned")

    /**
     * ref [DrawingEntity]
     */
    //val drawingList = varchar("drawingList", 1024)

    /**
     * ref [VoiceEntity]
     */
    //val voiceList = varchar("voiceList", 1024)

    /**
     * ref [LabelJoinNoteEntity]
     */
    //val labelList = varchar("labelList", 1024)

    /**
     * ref [CheckboxEntity]
     */
    //val checkboxList = varchar("checkboxList", 1024)

    //    val replyTo = integer("reply_to").index().nullable()
//    val directReplyTo = integer("direct_reply_to").index().nullable()
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object DrawingEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val path = varchar("path", 1024)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object VoiceEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val path = varchar("path", 1024)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object LabelJoinNoteEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val labelId = varchar("label_id", 128).index()
    val noteId = varchar("note_id", 128).index()
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object LabelEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val text = varchar("text", 128)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}

object CheckboxEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val text = varchar("text", 128)
    val indent = integer("indent")
    val checked = bool("checked")
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}