package com.notes.domain

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun getNoteList(): LiveData<List<NoteItem>>

    suspend fun addNoteItem(noteItem: NoteItem)

    suspend fun editNoteItem(noteItem: NoteItem)

    suspend fun getNoteItem(noteItemId: Long): NoteItem

    suspend fun deleteNoteItem(noteItem: NoteItem)
}