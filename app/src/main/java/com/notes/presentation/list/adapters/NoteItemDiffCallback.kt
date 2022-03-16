package com.notes.presentation.list.adapters

import androidx.recyclerview.widget.DiffUtil
import com.notes.domain.NoteItem

object NoteItemDiffCallback : DiffUtil.ItemCallback<NoteItem>() {

    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem == newItem
    }
}