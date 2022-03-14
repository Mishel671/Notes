package com.notes.domain

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun getNoteList(): LiveData<List<NoteItem>>

}