package com.notes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.notes.data.database.NoteDao
import com.notes.data.mapper.NoteMapper
import com.notes.domain.NoteItem
import com.notes.domain.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: NoteMapper
) : NoteRepository {

    override fun getNoteList(): LiveData<List<NoteItem>> {
        return Transformations.map(
            noteDao.getAll()
        ) {
            mapper.mapListDboToListEntity(it)
        }
    }

    override suspend fun addNoteItem(noteItem: NoteItem) {
        noteDao.addNoteItem(mapper.mapEntityToDbo(noteItem))
    }

    override suspend fun editNoteItem(noteItem: NoteItem) {
        noteDao.addNoteItem(mapper.mapEntityToDbo(noteItem))
    }

    override suspend fun getNoteItem(noteItemId: Long): NoteItem {
        val noteDbo = noteDao.getNoteItem(noteItemId)
        return mapper.mapDboToEntity(noteDbo)
    }

    override suspend fun deleteNoteItem(noteItem: NoteItem) {
        noteDao.deleteNoteItem(noteItem.id)
    }

}