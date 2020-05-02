package db.dao.note

import pojo.NoteModel
import java.io.Closeable

interface NoteDao : Closeable {

    fun init()

    fun getPinned(userId: String): List<NoteModel>

    fun getAll(userId: String): List<NoteModel>

    fun insert(noteModel: NoteModel)

    fun delete(noteModel: NoteModel)

    fun update(noteModel: NoteModel)

    fun get(id: String): NoteModel

    /**
     * checks for creation/modification time of the notes
     * and inserts the missing ones, updating others.
     * @returns the number of updates/inserts
     */
    fun sync(notes: List<NoteModel>): Int
}