package com.notes.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.notes.databinding.ListItemNoteBinding
import com.notes.domain.NoteItem

class NoteListAdapter : ListAdapter<NoteItem, NoteItemViewHolder>(NoteItemDiffCallback) {

    var onNoteItemClickListener: ((NoteItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val viewBinding = ListItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val noteItem = getItem(position)
        val viewBinding = holder.viewBinding
        with(viewBinding) {
            titleLabel.text = noteItem.title
            contentLabel.text = noteItem.content
            root.setOnClickListener {
                onNoteItemClickListener?.invoke(noteItem)
            }

        }
    }
}