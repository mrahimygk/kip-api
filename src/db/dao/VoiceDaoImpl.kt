package db.dao

import org.jetbrains.exposed.sql.Database
import pojo.VoiceModel

class VoiceDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : VoiceDao {
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<VoiceModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<VoiceModel> {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}