package com.notes.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.notes.data.database.NoteDao
import com.notes.data.database.NoteDbo
import com.notes.domain.NoteItem
import com.notes.domain.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNoteList(): LiveData<List<NoteItem>> {
        return Transformations.map(
            noteDao.getAll()
        ) {
            mapListDbToListEntity(it)
        }
    }

    private fun mapListDbToListEntity(list: List<NoteDbo>) = list.map {
        mapDbModelToEntity(it)
    }

    private fun mapDbModelToEntity(db: NoteDbo) = NoteItem(
        id = db.id,
        title = db.title,
        content = db.content
    )
}