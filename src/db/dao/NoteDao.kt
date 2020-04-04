package db.dao

import pojo.NoteModel
import java.io.Closeable

interface NoteDao : Closeable {

    fun init()

    fun getPinned(user: String): List<NoteModel>

    fun getAll(userId: String) : List<NoteModel>

    fun insert(noteModel: NoteModel): String

    fun delete(noteModel: NoteModel)

    fun update(noteModel: NoteModel)

    fun get(id: String): NoteModel
}