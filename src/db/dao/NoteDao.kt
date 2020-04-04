package db.dao

import pojo.NoteModel
import java.io.Closeable

interface NoteDao : Closeable {

    fun init()

    fun getPinned(userId: String): List<NoteModel>

    fun getAll(userId: String) : List<NoteModel>

    fun insert(noteModel: NoteModel)

    fun delete(noteModel: NoteModel)

    fun update(noteModel: NoteModel)

    fun get(id: String): NoteModel
}