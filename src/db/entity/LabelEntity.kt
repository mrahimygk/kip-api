package db.entity

import org.jetbrains.exposed.sql.Table

object LabelEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val text = varchar("text", 128)
    val created = datetime("created")
    val modified = datetime("modified")
}