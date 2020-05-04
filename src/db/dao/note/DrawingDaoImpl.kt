package db.dao.note

import db.entity.DrawingEntity
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import pojo.DrawingModel

class DrawingDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : DrawingDao {
    override fun init() {
        db.transaction {
            create(DrawingEntity)
        }
    }

    override fun getAll(): List<DrawingModel> = db.transaction {
        DrawingEntity.selectAll().map { extractRow(it) }
    }

    override fun get(id: String): DrawingModel = db.transaction {
        extractRow(DrawingEntity.select { DrawingEntity.id.eq(id) }.single())
    }

    override fun extractRow(row: ResultRow) =
        DrawingModel(
            row[DrawingEntity.id],
            row[DrawingEntity.path],
            row[DrawingEntity.created].toString(),
            row[DrawingEntity.modified].toString()
        )

    override fun getAllForNote(noteId: String): List<DrawingModel> = db.transaction {
        DrawingEntity.select { DrawingEntity.noteid.eq(noteId) }.map { extractRow(it) }
    }

    override fun insert(drawingModel: pojo.DrawingModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(drawingList: List<pojo.DrawingModel>, noteId: String) {
        db.transaction {
            DrawingEntity.batchInsert(drawingList) { drawing ->
                this[DrawingEntity.id] = drawing.id
                this[DrawingEntity.noteid] = noteId
                this[DrawingEntity.path] = drawing.path
                this[DrawingEntity.created] = DateTime(drawing.created)
                this[DrawingEntity.modified] = DateTime(drawing.modified)
            }
        }
    }

    override fun update(drawingModel: DrawingModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}