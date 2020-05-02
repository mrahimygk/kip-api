package db.entity

import org.jetbrains.exposed.sql.Table

object LabelEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val text = varchar("text", 128)
    val createdDate = datetime("created_date")
    val modifiedDate = datetime("modified_date")
}