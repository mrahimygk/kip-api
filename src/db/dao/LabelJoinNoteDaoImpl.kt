package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.LabelJoinNoteModel

class LabelJoinNoteDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : LabelJoinNoteDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<LabelJoinNoteModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<LabelJoinNoteModel> {
        TODO("Not yet implemented")
    }

    override fun insert(labelModel: LabelJoinNoteModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(labelModel: List<LabelJoinNoteModel>) {
        TODO("Not yet implemented")
    }

    override fun update(labelModel: LabelJoinNoteModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}