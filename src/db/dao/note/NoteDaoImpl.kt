package db.dao.note

import db.entity.LabelEntity
import db.entity.LabelJoinNoteEntity
import db.entity.NoteEntity
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import pojo.LabelJoinNoteModel
import pojo.NoteModel

class NoteDaoImpl(
    private val labelDao: LabelDao,
    private val labelJoinNoteDao: LabelJoinNoteDao,
    private val drawingDao: DrawingDao,
    private val voiceDao: VoiceDao,
    private val checkboxDao: CheckboxDao,
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : NoteDao {

    override fun init() {
        db.transaction {
            create(NoteEntity)
        }
    }

    override fun get(id: String) = db.transaction {
        getFullNoteData(NoteEntity.select { NoteEntity.id.eq(id) }.single(), id)
    }

    private fun getFullNoteData(row: ResultRow, id: String) = db.transaction {
        val labelJoinNoteList = labelJoinNoteDao.getAllForNote(id)
        //TODO: get by sql `where id in (ids)`
        val labels = labelDao.getAll().filter { labelJoinNoteList.map { e -> e.id }.contains(it.id) }
        val drawings = drawingDao.getAllForNote(id)
        val voices = voiceDao.getAllForNote(id)
        val checkBoxes = checkboxDao.getAllForNote(id)
        extractRow(row).copy(drawings = drawings, voices = voices, labels = labels, checkboxes = checkBoxes)
    }

    override fun delete(noteModel: NoteModel) {
        db.transaction {
            NoteEntity.deleteWhere { NoteEntity.id.eq(noteModel.id) }
        }
    }

    override fun getAll(userId: String) = db.transaction {
        NoteEntity.select { NoteEntity.email.eq(userId) }
            .map { row ->
                val id = row[NoteEntity.id]
                getFullNoteData(row, id)
            }
    }

    override fun getAll() = db.transaction {
        NoteEntity.selectAll().map { row ->
            val id = row[NoteEntity.id]
            getFullNoteData(row, id)
        }
    }

    override fun insert(noteModel: NoteModel) {
        db.transaction {
            NoteEntity.insert {
                it[id] = noteModel.id
                it[email] = noteModel.email
                it[title] = noteModel.title
                it[content] = noteModel.content
                it[color] = noteModel.color
                it[created] = DateTime(noteModel.created)
                it[modified] = DateTime(noteModel.modified)
            }

            drawingDao.batchInsert(noteModel.drawings, noteModel.id)

            checkboxDao.batchInsert(noteModel.checkboxes, noteModel.id)

            if (noteModel.labels.isNotEmpty()) {
                LabelEntity.batchInsert(noteModel.labels) { label ->
                    this[LabelEntity.id] = label.id
                    this[LabelEntity.text] = label.text
                    this[LabelEntity.created] = DateTime(label.created)
                    this[LabelEntity.modified] = DateTime(label.modified)
                }

                LabelJoinNoteEntity.batchInsert(noteModel.labels) { label ->
                    this[LabelJoinNoteEntity.id] = "${noteModel.id}${label.id}"
                    this[LabelJoinNoteEntity.labelid] = label.id
                    this[LabelJoinNoteEntity.noteid] = noteModel.id
                    this[LabelJoinNoteEntity.created] = DateTime(label.created)
                    this[LabelJoinNoteEntity.modified] = DateTime(label.modified)
                }
            }

            voiceDao.batchInsert(noteModel.voices, noteModel.id)
        }
    }

    override fun getPinned(userId: String): List<NoteModel> {
        //TODO: use sql where
        return getAll(userId).filter { it.pinned }
    }

    override fun update(noteModel: NoteModel) {
        db.transaction {
            NoteEntity.update({ NoteEntity.id eq noteModel.id }) {
                it[title] = noteModel.title
                it[content] = noteModel.content
                it[color] = noteModel.color
                it[pinned] = noteModel.pinned
                it[created] = DateTime(noteModel.created)
                it[modified] = DateTime(noteModel.modified)
            }

            //TODO: update label (tricky!!!)
            val updatingLabels =
                labelDao.getAll().filter { noteModel.labels.map { e -> e.id }.contains(it.id) }
            updatingLabels.forEach { e ->
                labelDao.update(e)
            }

            val insertingLabels =
                noteModel.labels.filterNot { updatingLabels.map { e -> e.id }.contains(it.id) }
            labelDao.batchInsert(insertingLabels, noteModel.id)

            //TODO: this maybe is handled by [labelDao]?
            labelJoinNoteDao.batchInsert(insertingLabels.map {
                //TODO: adding a uuid?
                LabelJoinNoteModel("", noteModel.id, it.id, DateTime().toString(), DateTime().toString())
            })

            val updatingVoices =
                voiceDao.getAll().filter { noteModel.voices.map { e -> e.id }.contains(it.id) }
            updatingVoices.forEach { e ->
                voiceDao.update(e)
            }

            val insertingVoices =
                noteModel.voices.filterNot { updatingVoices.map { e -> e.id }.contains(it.id) }
            voiceDao.batchInsert(insertingVoices, noteModel.id)

            val updatingDrawings =
                drawingDao.getAll().filter { noteModel.drawings.map { e -> e.id }.contains(it.id) }
            updatingDrawings.forEach { e ->
                drawingDao.update(e)
            }

            val insertingDrawings =
                noteModel.drawings.filterNot { updatingDrawings.map { e -> e.id }.contains(it.id) }
            drawingDao.batchInsert(insertingDrawings, noteModel.id)

            val updatingCheckboxes =
                checkboxDao.getAll().filter { noteModel.checkboxes.map { e -> e.id }.contains(it.id) }
            updatingCheckboxes.forEach { e ->
                checkboxDao.update(e)
            }

            val insertingCheckboxes =
                noteModel.checkboxes.filterNot { updatingCheckboxes.map { e -> e.id }.contains(it.id) }
            checkboxDao.batchInsert(insertingCheckboxes, noteModel.id)

            //TODO: removing elements that are not present. eg removed voice, removed drawing..
        }
    }

    override fun sync(notes: List<NoteModel>): Int {
        TODO("Not yet implemented")
    }

    override fun extractRow(row: ResultRow) = NoteModel(
        row[NoteEntity.id],
        row[NoteEntity.email],
        row[NoteEntity.title],
        row[NoteEntity.content],
        row[NoteEntity.color],
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        row[NoteEntity.pinned],
        row[NoteEntity.created].toString(),
        row[NoteEntity.modified].toString()
    )

//    override fun top(count: Int) = db.transaction {
//        TODO()
////        val k2 = NoteModel.alias("k2")
////        NoteModel.join(k2, JoinType.LEFT, NoteModel.id, k2[NoteModel.replyTo])
////            .slice(NoteModel.id, k2[NoteModel.id].count())
////            .selectAll()
////            .groupBy(NoteModel.id)
////            .orderBy(k2[NoteModel.id].count(), isAsc = false)
////            .limit(count)
////            .map { it[NoteModel.id] }
//    }

//    override fun latest(count: Int): List<Int> = db.transaction {
//        TODO()
////        var attempt = 0
////        var allCount: Int? = null
////
////        for (minutes in generateSequence(2) { it * it }) {
////            attempt++
////            val dt = DateTime.now().minusMinutes(minutes)
////
////            val all = NoteModel.slice(NoteModel.id)
////                .select { NoteModel.date.greater(dt) }
////                .orderBy(NoteModel.date, false)
////                .limit(count)
////                .map { it[NoteModel.id] }
////
////            if (all.size > count) return@transaction all
////            if (attempt > 10 && allCount == null) {
////                allCount = NoteModel.slice(NoteModel.id.count()).selectAll().count()
////                if (allCount <= count) {
////                    return@transaction NoteModel.slice(NoteModel.id).selectAll().map {
////                        it[NoteModel.id]
////                    }
////                }
////            }
////        }
////        emptyList()
//    }

//    override fun countReplies(id: Int) = db.transaction {
//        TODO()
////        NoteModel.slice(NoteModel.id.count()).select {
////            NoteModel.replyTo.eq(id)
////        }.single()[NoteModel.id.count()]
//    }

//    override fun createKweet(user: String, text: String, replyTo: Int?, date: DateTime) = db.transaction {
//        TODO()
//        NoteModel.insert {
//            it[NoteModel.user] = user
//            it[NoteModel.date] = date
//            it[NoteModel.replyTo] = replyTo
//            it[NoteModel.text] = text
//        }.generatedKey ?: throw IllegalStateException("No generated key returned")
//    }


//    override fun userKweets(userId: String) = db.transaction {
//        TODO()
////        NoteModel.slice(NoteModel.id).select {
////            NoteModel.user.eq(userId)
////        }.orderBy(NoteModel.date, false).limit(100).map {
////            it[NoteModel.id]
////        }
//    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}