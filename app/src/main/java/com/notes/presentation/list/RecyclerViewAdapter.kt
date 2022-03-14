package com.notes.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.notes.databinding.ListItemNoteBinding
import com.notes.domain.NoteItem

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val items = mutableListOf<NoteItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ListItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(
        items: List<NoteItem>
    ) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(
            note: NoteItem
        ) {
            binding.titleLabel.text = note.title
            binding.contentLabel.text = note.content
        }

    }

}