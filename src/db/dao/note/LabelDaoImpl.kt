package db.dao.note

import db.entity.LabelEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import pojo.LabelModel

class LabelDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : LabelDao {
    override fun init() {
        db.transaction {
            create(LabelEntity)
        }
    }

    override fun getAll(): List<LabelModel> = db.transaction {
        LabelEntity.selectAll().map { extractRow(it) }
    }

    override fun get(id: String): LabelModel = db.transaction {
        extractRow(LabelEntity.select { LabelEntity.id.eq(id) }.single())
    }

    override fun getAllForNote(noteId: String): List<LabelModel> = db.transaction {
        TODO("Not yet implemented")
    }

    override fun extractRow(row: ResultRow) = LabelModel(
        row[LabelEntity.id],
        row[LabelEntity.text],
        row[LabelEntity.created].toString(),
        row[LabelEntity.modified].toString()
    )

    override fun insert(labelModel: LabelModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(labelModel: List<LabelModel>, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun update(labelModel: LabelModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}