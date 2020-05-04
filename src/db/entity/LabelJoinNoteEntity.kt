package db.entity

import org.jetbrains.exposed.sql.Table

object LabelJoinNoteEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val labelid = varchar("labelid", 128).index()
    val noteid = varchar("noteid", 128).index()
    val created = datetime("created")
    val modified = datetime("modified")
}