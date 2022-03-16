package com.notes.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    operator fun invoke() = noteRepository.getNoteList()
}