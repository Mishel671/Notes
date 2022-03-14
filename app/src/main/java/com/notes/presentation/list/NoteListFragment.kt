package com.notes.presentation.list

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.notes.NoteApp
import com.notes.databinding.FragmentNoteListBinding
import com.notes.presentation.ViewModelFactory
import com.notes.presentation._base.FragmentNavigator
import com.notes.presentation._base.ViewBindingFragment
import com.notes.presentation._base.findImplementationOrThrow
import com.notes.presentation.details.NoteDetailsFragment
import com.notes.presentation.list.adapters.NoteListAdapter
import javax.inject.Inject

class NoteListFragment : ViewBindingFragment<FragmentNoteListBinding>(
    FragmentNoteListBinding::inflate
) {

    private val component by lazy {
        (requireActivity().application as NoteApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NoteListViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewBindingCreated(
        viewBinding: FragmentNoteListBinding,
        savedInstanceState: Bundle?
    ) {
        super.onViewBindingCreated(viewBinding, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteListViewModel::class.java]
        viewBinding.createNoteButton.setOnClickListener {
            viewModel.onCreateNoteClick()
        }
        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView(){
        val adapter = NoteListAdapter()
        with(viewBinding!!) {
            list.adapter = adapter
            list.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )
        }
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToNoteCreation.observe(viewLifecycleOwner) {
            findImplementationOrThrow<FragmentNavigator>()
                .navigateTo(
                    NoteDetailsFragment()
                )

        }
    }

    companion object {
        fun newInstance() = NoteListFragment()
    }


}