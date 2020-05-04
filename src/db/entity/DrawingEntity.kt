package db.entity

import org.jetbrains.exposed.sql.Table

object DrawingEntity : Table() {
    val id = varchar("id", 128).primaryKey()
    val noteid = varchar("noteid", 128).index()
    val path = varchar("path", 1024)
    val created = datetime("created")
    val modified = datetime("modified")
}