package db.dao

import db.entity.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import pojo.User
import java.io.File

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

    override fun getUser(email: String, hash: String?) = db.transaction {
        Users.select {
            Users.email.eq(email)
        }.mapNotNull {
            if (hash == null || it[Users.hash] == hash)
                User(it[Users.email], it[Users.avatar], it[Users.hash])
            else null
        }.singleOrNull()
    }

    override fun createUser(user: User) = db.transaction {
        Users.insert {
            it[avatar] = user.avatar
            it[email] = user.email
            it[hash] = user.hash
        }
    }

    override fun close() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}