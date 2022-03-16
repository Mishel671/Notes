package com.notes.domain

import androidx.room.ColumnInfo
import java.time.LocalDateTime

data class NoteItem (
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    var id: Long = UNDEFINED_ID
) {
    companion object{
        const val UNDEFINED_ID = 0L
    }
}