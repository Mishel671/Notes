package com.notes.domain

import javax.inject.Inject

class EditNoteItemUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteItem: NoteItem) = noteRepository.editNoteItem(noteItem)
}