package com.notes.domain

import javax.inject.Inject

class GetNoteItemUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: Long) = noteRepository.getNoteItem(noteId)
}