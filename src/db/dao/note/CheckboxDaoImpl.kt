package db.dao.note

import db.entity.CheckboxEntity
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import pojo.CheckboxModel

class CheckboxDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : CheckboxDao {
    override fun init() {
        db.transaction {
            create(CheckboxEntity)
        }
    }

    override fun getAll(): List<CheckboxModel> = db.transaction {
        CheckboxEntity.selectAll().map { extractRow(it) }
    }

    override fun get(id: String): CheckboxModel = db.transaction {
        extractRow(CheckboxEntity.select { CheckboxEntity.id.eq(id) }.single())
    }

    override fun getAllForNote(noteId: String): List<CheckboxModel> = db.transaction {
        CheckboxEntity.select { CheckboxEntity.noteid.eq(noteId) }
            .map { extractRow(it) }
    }

    override fun extractRow(row: ResultRow): CheckboxModel =
        CheckboxModel(
            row[CheckboxEntity.id],
            row[CheckboxEntity.noteid],
            row[CheckboxEntity.text],
            row[CheckboxEntity.indent],
            row[CheckboxEntity.checked],
            row[CheckboxEntity.created].toString(),
            row[CheckboxEntity.modified].toString()
        )

    override fun insert(checkboxModel: CheckboxModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(checkboxList: List<CheckboxModel>, noteId: String) {
        db.transaction {
            CheckboxEntity.batchInsert(checkboxList) { checkbox ->
                this[CheckboxEntity.id] = checkbox.id
                this[CheckboxEntity.noteid] = noteId
                this[CheckboxEntity.text] = checkbox.text
                this[CheckboxEntity.indent] = checkbox.indent
                this[CheckboxEntity.checked] = checkbox.checked
                this[CheckboxEntity.created] = DateTime(checkbox.created)
                this[CheckboxEntity.modified] = DateTime(checkbox.modified)
            }
        }
    }

    override fun update(checkboxModel: CheckboxModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}