package com.notes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notes.domain.DeleteNoteItemUseCase
import com.notes.domain.GetNoteListUseCase
import com.notes.domain.NoteItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteItemUseCase: DeleteNoteItemUseCase
) : ViewModel() {

    val notes = getNoteListUseCase()

    fun deleteNoteItem(noteItem: NoteItem) {
        viewModelScope.launch {
            deleteNoteItemUseCase(noteItem)
        }
    }
}
