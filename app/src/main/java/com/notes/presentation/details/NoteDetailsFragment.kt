package com.notes.presentation.details

import com.notes.databinding.FragmentNoteDetailsBinding
import com.notes.presentation._base.ViewBindingFragment

class NoteDetailsFragment : ViewBindingFragment<FragmentNoteDetailsBinding>(
    FragmentNoteDetailsBinding::inflate
) {

    companion object{
        fun newInstance() = NoteDetailsFragment()
    }
}