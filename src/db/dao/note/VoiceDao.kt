package db.dao.note

import db.dao.BaseDao
import pojo.VoiceModel
import java.io.Closeable

interface VoiceDao : Closeable, BaseDao<VoiceModel> {
    fun insert(voiceModel: VoiceModel, noteId: String)
    fun batchInsert(voiceList: List<VoiceModel>, noteId: String)
    fun update(voiceModel: VoiceModel)
    fun getAllForNote(noteId: String): List<VoiceModel>
}