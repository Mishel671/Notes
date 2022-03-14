package com.notes.presentation.list

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.notes.App
import com.notes.databinding.FragmentNoteListBinding
import com.notes.presentation.ViewModelFactory
import com.notes.presentation._base.FragmentNavigator
import com.notes.presentation._base.ViewBindingFragment
import com.notes.presentation._base.findImplementationOrThrow
import com.notes.presentation.details.NoteDetailsFragment
import javax.inject.Inject

class NoteListFragment : ViewBindingFragment<FragmentNoteListBinding>(
    FragmentNoteListBinding::inflate
) {

    private val component by lazy {
        (requireActivity().application as App).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NoteListViewModel::class.java]
    }

    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }
    override fun onViewBindingCreated(
        viewBinding: FragmentNoteListBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewBindingCreated(viewBinding, savedInstanceState)

        viewBinding.list.adapter = recyclerViewAdapter
        viewBinding.list.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        viewBinding.createNoteButton.setOnClickListener {
            viewModel.onCreateNoteClick()
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerViewAdapter.setItems(it)
            }
        }
        viewModel.navigateToNoteCreation.observe(viewLifecycleOwner) {
            findImplementationOrThrow<FragmentNavigator>()
                .navigateTo(
                    NoteDetailsFragment()
                )

        }
    }


    companion object{
        fun newInstance() = NoteListFragment()
    }


}