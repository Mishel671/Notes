package com.notes.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    fun getNoteList(): LiveData<List<NoteItem>>{
        return noteRepository.getNoteList()
    }
}