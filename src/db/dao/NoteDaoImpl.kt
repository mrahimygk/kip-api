package db.dao

import db.entity.*
import org.jetbrains.exposed.sql.*

class NoteDaoImpl(
    private val labelDao: LabelDao,
    private val drawingDao: DrawingDao,
    private val voiceDao: VoiceDao,
    private val checkboxDao: CheckboxDao,
    private val db: Database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
) : NoteDao {

    override fun init() {
        db.transaction {
            create(NoteModel)
        }
    }

    override fun get(id: String) = db.transaction {
        val row = NoteModel.select { NoteModel.id.eq(id) }.single()
        val labels = labelDao.get(id)
        val drawings = drawingDao.get(id)
        val voices = voiceDao.get(id)
        val checkBoxes = checkboxDao.get(id)

        pojo.NoteModel(
            row[NoteModel.id],
            row[NoteModel.userEmail],
            row[NoteModel.title],
            row[NoteModel.content],
            row[NoteModel.color],
            drawings,
            voices,
            labels,
            checkBoxes,
            row[NoteModel.createdDate],
            row[NoteModel.modifiedDate]
        )

    }

    override fun delete(noteModel: pojo.NoteModel) {
        db.transaction {
            NoteModel.deleteWhere { NoteModel.id.eq(noteModel.id) }
        }
    }

    override fun getAll(userId: String) = db.transaction {
        val tuples = NoteModel.select { NoteModel.userEmail.eq(userId) }
        tuples.map { row ->
            get(row[NoteModel.id])
        }
    }

    override fun insert(noteModel: pojo.NoteModel) {
        db.transaction {
            NoteModel.insert {
                it[id] = noteModel.id
                it[userEmail] = noteModel.userEmail
                it[title] = noteModel.title
                it[content] = noteModel.content
                it[color] = noteModel.color
                it[createdDate] = noteModel.createdDate
                it[modifiedDate] = noteModel.modifiedDate
            }

            if (noteModel.drawingList.isNotEmpty()) {
                DrawingModel.batchInsert(noteModel.drawingList) { drawing ->
                    this[DrawingModel.id] = drawing.id
                    this[DrawingModel.noteId] = noteModel.id
                    this[DrawingModel.path] = drawing.path
                    this[DrawingModel.createdDate] = drawing.createdDate
                    this[DrawingModel.modifiedDate] = drawing.modifiedDate
                }
            }

            if (noteModel.checkboxList.isNotEmpty()) {
                CheckboxModel.batchInsert(noteModel.checkboxList) { checkbox ->
                    this[CheckboxModel.id] = checkbox.id
                    this[CheckboxModel.noteId] = noteModel.id
                    this[CheckboxModel.text] = checkbox.text
                    this[CheckboxModel.indent] = checkbox.indent
                    this[CheckboxModel.checked] = checkbox.checked
                    this[CheckboxModel.createdDate] = checkbox.createdDate
                    this[CheckboxModel.modifiedDate] = checkbox.modifiedDate
                }
            }

            if (noteModel.labelList.isNotEmpty()) {
                LabelModel.batchInsert(noteModel.labelList) { label ->
                    this[LabelModel.id] = label.id
                    this[LabelModel.text] = label.text
                    this[LabelModel.createdDate] = label.createdDate
                    this[LabelModel.modifiedDate] = label.modifiedDate
                }

                LabelJoinNoteModel.batchInsert(noteModel.labelList) { label ->
                    this[LabelJoinNoteModel.id] = "${noteModel.id}${label.id}"
                    this[LabelJoinNoteModel.labelId] = label.id
                    this[LabelJoinNoteModel.noteId] = noteModel.id
                    this[LabelJoinNoteModel.createdDate] = label.createdDate
                    this[LabelJoinNoteModel.modifiedDate] = label.modifiedDate
                }
            }

            if (noteModel.voiceList.isNotEmpty()) {
                VoiceModel.batchInsert(noteModel.voiceList) { voice ->
                    this[VoiceModel.id] = voice.id
                    this[VoiceModel.noteId] = noteModel.id
                    this[VoiceModel.path] = voice.path
                    this[VoiceModel.createdDate] = voice.createdDate
                    this[VoiceModel.modifiedDate] = voice.modifiedDate
                }
            }
        }
    }

    override fun getPinned(userId: String): List<pojo.NoteModel> {
        return getAll(userId).filter { it.isPinned }
    }

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

    override fun createKweet(user: String, text: String, replyTo: Int?, date: DateTime) = db.transaction {
        TODO()
        NoteModel.insert {
            it[NoteModel.user] = user
            it[NoteModel.date] = date
            it[NoteModel.replyTo] = replyTo
            it[NoteModel.text] = text
        }.generatedKey ?: throw IllegalStateException("No generated key returned")
    }


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