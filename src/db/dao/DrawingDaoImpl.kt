package db.dao

import db.entity.DrawingModel
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert

class DrawingDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : DrawingDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<pojo.DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<pojo.DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun insert(drawingModel: pojo.DrawingModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(drawingList: List<pojo.DrawingModel>, noteId: String) {
        db.transaction {
            DrawingModel.batchInsert(drawingList) { drawing ->
                this[DrawingModel.id] = drawing.id
                this[DrawingModel.noteId] = noteId
                this[DrawingModel.path] = drawing.path
                this[DrawingModel.createdDate] = drawing.createdDate
                this[DrawingModel.modifiedDate] = drawing.modifiedDate
            }
        }
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}