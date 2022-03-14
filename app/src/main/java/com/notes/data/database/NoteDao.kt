package com.notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<NoteDbo>>

    @Insert
    fun insertAll(vararg notes: NoteDbo)

}