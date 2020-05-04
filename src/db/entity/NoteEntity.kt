package db.entity

import org.jetbrains.exposed.sql.Table

object NoteEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val email = varchar("email", 128).index()
    val title = varchar("title", 1024)
    val content = varchar("content", 2048)
    val color = varchar("color", 10)
    val pinned = bool("pinned").default(false)

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

    val created = datetime("created")
    val modified = datetime("modified")
}
