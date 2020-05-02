package db.dao.note

import db.entity.VoiceEntity
import org.jetbrains.exposed.sql.*
import pojo.VoiceModel

class VoiceDaoImpl(
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

) : VoiceDao {
    override fun init() {
        db.transaction {
            create(VoiceEntity)
        }
    }

    override fun getAll(): List<VoiceModel> = db.transaction {
        VoiceEntity.selectAll().map { extractRow(it) }
    }

    override fun get(id: String): VoiceModel = db.transaction {
        extractRow(VoiceEntity.select { VoiceEntity.id.eq(id) }.single())
    }

    override fun getAllForNote(noteId: String): List<VoiceModel> = db.transaction {
        VoiceEntity.select { VoiceEntity.noteId.eq(noteId) }.map { extractRow(it) }
    }

    override fun extractRow(row: ResultRow) = VoiceModel(
        row[VoiceEntity.id],
        row[VoiceEntity.path],
        row[VoiceEntity.createdDate],
        row[VoiceEntity.modifiedDate]
    )

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