package db.dao

import pojo.Kweet
import org.joda.time.DateTime
import java.io.Closeable

interface KweetDao : Closeable {

    fun init()

    fun top(count:Int=10): List<Int>

    fun latest(count: Int=10) : List<Int>

    fun countReplies(id: Int): Int

    fun createKweet(user: String, text: String, replyTo: Int? = null, date: DateTime = DateTime.now()): Int

    fun deleteKweet(id: Int)

    fun getKweet(id: Int): Kweet

    fun userKweets(userId: String): List<Int>
}