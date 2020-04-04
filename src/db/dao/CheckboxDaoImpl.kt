package db.dao

import db.entity.CheckboxEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
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

    override fun batchInsert(checkboxList: List<CheckboxModel>, noteId: String) {
        db.transaction {
            CheckboxEntity.batchInsert(checkboxList) { checkbox ->
                this[CheckboxEntity.id] = checkbox.id
                this[CheckboxEntity.noteId] = noteId
                this[CheckboxEntity.text] = checkbox.text
                this[CheckboxEntity.indent] = checkbox.indent
                this[CheckboxEntity.checked] = checkbox.checked
                this[CheckboxEntity.createdDate] = checkbox.createdDate
                this[CheckboxEntity.modifiedDate] = checkbox.modifiedDate
            }
        }
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}