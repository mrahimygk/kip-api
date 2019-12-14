package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.User
import java.io.File
import db.entity.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDaoImpl(val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")) : UserDao {
    constructor(dir: File) : this(
        Database.connect(
            "jdbc:h2:file:${dir.canonicalFile.absolutePath}",
            driver = "org.h2.Driver"
        )
    )

    override fun init() {
        db.transaction {
            create(Users)
        }
    }

    override fun getUser(userId: String, hash: String?) = db.transaction {
        Users.select {
            Users.id.eq(userId)
        }.mapNotNull {
            if (hash == null || it[Users.hash] == hash)
                User(userId, it[Users.email], it[Users.name], it[Users.hash])
            else null
        }.singleOrNull()
    }

    override fun getUser(email: String) = db.transaction {
        Users.select { Users.email.eq(email) }
            .map {
                User(it[Users.id], email, it[Users.name], it[Users.hash])
            }.singleOrNull()
    }

    override fun createUser(user: User) = db.transaction {
        Users.insert {
            it[id] = user.userID
            it[name] = user.name
            it[email] = user.email
            it[hash] = user.hash
        }
    }

    override fun close() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}