package db.dao

import pojo.VoiceModel
import java.io.Closeable

interface VoiceDao : Closeable {

    fun init()

    fun getAll(): List<VoiceModel>
    fun get(noteId: String): List<VoiceModel>
    fun insert(voiceModel: VoiceModel, noteId: String)
    fun batchInsert(voiceList: List<VoiceModel>, noteId: String)
}