package com.notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY modifiedAt DESC")
    fun getAll(): LiveData<List<NoteDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteItem(noteDbo: NoteDbo)

    @Query("SELECT * FROM notes WHERE id=:noteItemId LIMIT 1")
    suspend fun getNoteItem(noteItemId: Long): NoteDbo

    @Query("DELETE FROM notes WHERE id=:noteItemId")
    suspend fun deleteNoteItem(noteItemId: Long)
}