package com.notes.presentation.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notes.data.database.NoteDao
import com.notes.data.database.NoteDatabase
import com.notes.domain.DeleteNoteItemUseCase
import com.notes.domain.GetNoteListUseCase
import com.notes.domain.NoteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteItemUseCase: DeleteNoteItemUseCase
) : ViewModel() {

    val notes = getNoteListUseCase()



    fun deleteNoteItem(noteItem: NoteItem){
        viewModelScope.launch {
            deleteNoteItemUseCase(noteItem)
        }
    }
}
