package db.entity

import org.jetbrains.exposed.sql.Table

object DrawingEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteId = varchar("note_id", 128).index()
    val path = varchar("path", 1024)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}