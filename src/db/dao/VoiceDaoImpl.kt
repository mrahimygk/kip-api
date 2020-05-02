package db.dao

import db.entity.VoiceEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import pojo.VoiceModel

class VoiceDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : VoiceDao {
    override fun init() {
        db.transaction {
            create(VoiceEntity)
        }
    }

    override fun getAll(): List<VoiceModel> {
        TODO("Not yet implemented")
    }

    override fun get(noteId: String): List<VoiceModel> {
        TODO("Not yet implemented")
    }

    override fun insert(voiceModel: VoiceModel, noteId: String) {
        TODO("Not yet implemented")
    }

    override fun batchInsert(voiceList: List<VoiceModel>, noteId: String) {
        db.transaction {
            VoiceEntity.batchInsert(voiceList) { voice ->
                this[VoiceEntity.id] = voice.id
                this[VoiceEntity.noteId] = noteId
                this[VoiceEntity.path] = voice.path
                this[VoiceEntity.createdDate] = voice.createdDate
                this[VoiceEntity.modifiedDate] = voice.modifiedDate
            }
        }
    }

    override fun update(voiceModel: VoiceModel) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}