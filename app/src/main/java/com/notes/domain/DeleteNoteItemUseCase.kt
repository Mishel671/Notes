package com.notes.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class DeleteNoteItemUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(noteItem: NoteItem) = noteRepository.deleteNoteItem(noteItem)
}