package com.notes.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notes.domain.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    private val getNoteItemUseCase: GetNoteItemUseCase,
    private val editNoteItemUseCase: EditNoteItemUseCase,
    private val addNoteItemUseCase: AddNoteItemUseCase
) : ViewModel() {

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean>
    get() = _errorInputTitle

    private val _errorInputContent = MutableLiveData<Boolean>()
    val errorInputContent: LiveData<Boolean>
    get() = _errorInputContent

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    private val _noteItem = MutableLiveData<NoteItem>()
    val noteItem: LiveData<NoteItem>
        get() = _noteItem

    fun getNoteItem(shopItemId: Long) {
        viewModelScope.launch {
            val item = getNoteItemUseCase(shopItemId)
            _noteItem.value = item
        }
    }

    init {
        Log.d("ViewModelLog", "$this")
    }

    fun addNoteItem(inputTitle: String?, inputContent: String?) {
        val title = parseString(inputTitle)
        val content = parseString(inputContent)
        val noteDate = LocalDateTime.now()
        val fieldsValid = validateInput(title, content)
        if (fieldsValid) {
            viewModelScope.launch {
                val noteItem = NoteItem(
                    title = title,
                    content = content,
                    createdAt = noteDate,
                    modifiedAt = noteDate
                )
                addNoteItemUseCase(noteItem)
                finishWork()
            }
        }
    }

    fun editNoteItem(inputTitle: String?, inputContent: String?) {
        val title = parseString(inputTitle)
        val content = parseString(inputContent)
        val modifiedDate = LocalDateTime.now()
        val fieldValid = validateInput(title, content)
        if (fieldValid) {
            _noteItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(title = title, content = content, modifiedAt = modifiedDate)
                    editNoteItemUseCase(item)
                    finishWork()
                }
            }
        }
    }

    private fun validateInput(title: String, content: String): Boolean {
        var result = true
        if (title.isBlank()) {
            _errorInputTitle.value = true
            result = false
        }
        if (content.isBlank()) {
            _errorInputContent.value = true
            result = false
        }
        return result
    }

    private fun parseString(inputValue: String?): String {
        return inputValue?.trim() ?: ""
    }

    fun resetErrorInputTitle() {
        _errorInputTitle.value = false
    }

    fun resetErrorInputContent() {
        _errorInputContent.value = false
    }


    private fun finishWork() {
        _closeScreen.value = Unit
    }
}