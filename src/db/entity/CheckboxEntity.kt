package db.entity

import org.jetbrains.exposed.sql.Table

object CheckboxEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val text = varchar("text", 128)
    val indent = integer("indent")
    val checked = bool("checked")
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}