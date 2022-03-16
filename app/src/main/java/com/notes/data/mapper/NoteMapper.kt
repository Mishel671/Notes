package com.notes.data.mapper

import com.notes.data.database.NoteDbo
import com.notes.domain.NoteItem
import javax.inject.Inject

class NoteMapper @Inject constructor() {

    fun mapListDboToListEntity(list: List<NoteDbo>) = list.map {
        mapDboToEntity(it)
    }

    fun mapDboToEntity(db: NoteDbo) = NoteItem(
        id = db.id,
        title = db.title,
        content = db.content,
        createdAt = db.createdAt,
        modifiedAt = db.modifiedAt
    )

    fun mapEntityToDbo(item: NoteItem) = NoteDbo(
        id = item.id,
        title = item.title,
        content = item.content,
        createdAt = item.createdAt,
        modifiedAt = item.modifiedAt
    )
}