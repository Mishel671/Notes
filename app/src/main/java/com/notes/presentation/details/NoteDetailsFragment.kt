package com.notes.presentation.details

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.notes.NoteApp
import com.notes.databinding.FragmentNoteDetailsBinding
import com.notes.presentation.ViewModelFactory
import com.notes.presentation._base.ViewBindingFragment
import com.notes.presentation.list.NoteListViewModel
import javax.inject.Inject

class NoteDetailsFragment : ViewBindingFragment<FragmentNoteDetailsBinding>(
    FragmentNoteDetailsBinding::inflate
) {


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewBindingCreated(
        viewBinding: FragmentNoteDetailsBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewBindingCreated(viewBinding, savedInstanceState)

    }

    companion object{
        fun newInstance() = NoteDetailsFragment()
    }
}