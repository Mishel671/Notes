package com.notes.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notes.domain.GetNoteListUseCase
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase
) : ViewModel() {

    val notes = getNoteListUseCase.getNoteList()

    private val _navigateToNoteCreation = MutableLiveData<Unit?>()
    val navigateToNoteCreation: LiveData<Unit?> = _navigateToNoteCreation


}