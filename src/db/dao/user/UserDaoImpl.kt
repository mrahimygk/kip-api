package db.dao.user

import db.entity.UserEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import pojo.UserModel
import java.io.File

class UserDaoImpl(val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")) :
    UserDao {
    constructor(dir: File) : this(
        Database.connect(
            "jdbc:h2:file:${dir.canonicalFile.absolutePath}",
            driver = "org.h2.Driver"
        )
    )

    override fun init() {
        db.transaction {
            create(UserEntity)
        }
    }

    override fun getUser(email: String, hash: String?) = db.transaction {
        UserEntity.select {
            UserEntity.email.eq(email)
        }.mapNotNull {
            if (hash == null || it[UserEntity.hash] == hash)
                UserModel(
                    it[UserEntity.email],
                    it[UserEntity.avatar],
                    it[UserEntity.hash],
                    it[UserEntity.token],
                    it[UserEntity.refreshtoken]
                )
            else null
        }.singleOrNull()
    }

    override fun getUserByEmail(email: String) = db.transaction {
        UserEntity.select {
            UserEntity.email.eq(email)
        }.mapNotNull {
            UserModel(
                it[UserEntity.email],
                it[UserEntity.avatar],
                it[UserEntity.hash],
                it[UserEntity.token],
                it[UserEntity.refreshtoken]
            )
        }.singleOrNull()
    }

    override fun createUser(user: UserModel) = db.transaction {
        UserEntity.insert {
            it[avatar] = user.avatar
            it[email] = user.email
            it[hash] = user.hash
            it[token] = user.token
            it[refreshtoken] = user.refreshToken
        }
    }

    override fun close() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}