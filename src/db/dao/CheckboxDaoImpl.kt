package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.CheckboxModel

class CheckboxDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : CheckboxDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<CheckboxModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<CheckboxModel> {
        TODO("Not yet implemented")
    }

    override fun insert(checkboxModel: CheckboxModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}