package db.entity

import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val email = varchar("email", 128).primaryKey()
    val avatar = varchar("avatar", 256)
    val hash = varchar("hash", 64)
}