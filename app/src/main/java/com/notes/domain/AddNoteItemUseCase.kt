package com.notes.domain

import javax.inject.Inject

class AddNoteItemUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteItem: NoteItem) = noteRepository.addNoteItem(noteItem)
}