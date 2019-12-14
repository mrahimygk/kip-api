package db.entity

import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id = varchar("id", 20).primaryKey()
    val email = varchar("email", 128).uniqueIndex()
    val name = varchar("name", 256)
    val hash = varchar("hash", 64)
}