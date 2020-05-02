package db.dao

import db.entity.DrawingEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import pojo.DrawingModel

class DrawingDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : DrawingDao {
    override fun init() {
        db.transaction {
            create(DrawingEntity)
        }
    }

    override fun getAll(): List<pojo.DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun get(drawingId: String): DrawingModel {
        TODO("Not yet implemented")
    }

    override fun getAllForNote(noteId: String): List<DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun insert(drawingModel: pojo.DrawingModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(drawingList: List<pojo.DrawingModel>, noteId: String) {
        db.transaction {
            DrawingEntity.batchInsert(drawingList) { drawing ->
                this[DrawingEntity.id] = drawing.id
                this[DrawingEntity.noteId] = noteId
                this[DrawingEntity.path] = drawing.path
                this[DrawingEntity.createdDate] = drawing.createdDate
                this[DrawingEntity.modifiedDate] = drawing.modifiedDate
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