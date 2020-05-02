package db.entity

import org.jetbrains.exposed.sql.Table

object LabelJoinNoteEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val labelId = varchar("label_id", 128).index()
    val noteId = varchar("note_id", 128).index()
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}