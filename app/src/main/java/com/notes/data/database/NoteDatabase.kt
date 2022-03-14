package com.notes.data.database

import android.content.Context
import androidx.room.*

@Database(
    entities = [
        NoteDbo::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class NoteDatabase : RoomDatabase() {

    companion object {

        private var db: NoteDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): NoteDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        NoteDatabase::class.java,
                        DB_NAME
                    ).createFromAsset("database-note.db")
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun noteDao(): NoteDao
}
