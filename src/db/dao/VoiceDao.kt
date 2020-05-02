package db.dao

import pojo.VoiceModel
import java.io.Closeable

interface VoiceDao : Closeable {

    fun init()

    fun getAll(): List<VoiceModel>
    fun get(voiceId: String): VoiceModel
    fun getAllForNote(noteId: String): List<VoiceModel>
    fun insert(voiceModel: VoiceModel, noteId: String)
    fun batchInsert(voiceList: List<VoiceModel>, noteId: String)
    fun update(voiceModel: VoiceModel)
}