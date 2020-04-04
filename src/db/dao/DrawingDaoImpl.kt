package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.DrawingModel

class DrawingDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : DrawingDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<DrawingModel> {
        TODO("Not yet implemented")
    }

    override fun insert(drawingModel: DrawingModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}