package com.notes.presentation.details

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.notes.NoteApp
import com.notes.R
import com.notes.databinding.FragmentNoteDetailsBinding
import com.notes.domain.NoteItem
import com.notes.presentation.ViewModelFactory
import com.notes.presentation._base.ViewBindingFragment
import javax.inject.Inject

class NoteDetailsFragment : ViewBindingFragment<FragmentNoteDetailsBinding>(
    FragmentNoteDetailsBinding::inflate
) {
    private val component by lazy {
        (requireActivity().application as NoteApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NoteDetailsViewModel


    private var screenMode: String = MODE_UNKNOWN
    private var noteItemId: Long = NoteItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewBindingCreated(
        viewBinding: FragmentNoteDetailsBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewBindingCreated(viewBinding, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteDetailsViewModel::class.java]
        setTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchAddMode() {
        viewBinding?.buttonSave?.setOnClickListener {
            val title = viewBinding?.etTitle?.text.toString()
            val content = viewBinding?.etContent?.text.toString()
            viewModel.addNoteItem(title, content)
        }
    }
    private fun launchEditMode() {
        viewModel.getNoteItem(noteItemId)
        viewModel.noteItem.observe(viewLifecycleOwner) {
            viewBinding?.etTitle?.setText(it.title)
            viewBinding?.etContent?.setText(it.content)
        }
        viewBinding?.buttonSave?.setOnClickListener {
            val title = viewBinding?.etTitle?.text.toString()
            val content = viewBinding?.etContent?.text.toString()
            viewModel.editNoteItem(title, content)
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputTitle.observe(viewLifecycleOwner) {
            val error = if (it) {
                getString(R.string.empty_title)
            } else {
                null
            }
            viewBinding?.tilTitle?.error = error
        }
        viewModel.errorInputContent.observe(viewLifecycleOwner) {
            val error = if (it) {
                getString(R.string.empty_title)
            } else {
                null
            }
            viewBinding?.tilContent?.error = error
        }

        viewModel.closeScreen.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }


    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(NOTE_ITEM_ID)) {
                throw RuntimeException("Param note item id is absent")
            }
            noteItemId = args.getLong(NOTE_ITEM_ID, NoteItem.UNDEFINED_ID)
        }
    }


    private fun setTextChangeListener() {
        viewBinding?.etTitle?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputTitle()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        viewBinding?.etContent?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputContent()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    companion object {
        const val NAME = "NoteDetailsFragment"

        private const val SCREEN_MODE = "extra_mode"
        private const val NOTE_ITEM_ID = "extra_note_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): NoteDetailsFragment {
            return NoteDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(noteItemId: Long): NoteDetailsFragment {
            return NoteDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putLong(NOTE_ITEM_ID, noteItemId)
                }
            }
        }
    }
}