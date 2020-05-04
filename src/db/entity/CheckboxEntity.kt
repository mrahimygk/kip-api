package db.entity

import org.jetbrains.exposed.sql.Table

object CheckboxEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteid = varchar("noteid", 128).index()
    val text = varchar("text", 128)
    val indent = integer("indent")
    val checked = bool("checked")
    val created = datetime("created")
    val modified = datetime("modified")
}