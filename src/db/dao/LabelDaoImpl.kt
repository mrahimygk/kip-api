package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.LabelModel

class LabelDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : LabelDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<LabelModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<LabelModel> {
        TODO("Not yet implemented")
    }

    override fun insert(labelModel: LabelModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}