package db.dao

import java.io.Closeable

interface LabelDao : Closeable {

    fun init()

    fun get(): List<String>
}