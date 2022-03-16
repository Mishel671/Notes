package com.notes.presentation.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var noteListAdapter: NoteListAdapter

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

        setRecyclerView()
        observeViewModel()

        viewBinding.createNoteButton.setOnClickListener {
            findImplementationOrThrow<FragmentNavigator>()
                .navigateTo(
                    NoteDetailsFragment.newInstanceAddItem(),
                    NoteDetailsFragment.NAME
                )
        }
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
            Log.d("ListLog", "$it")
        }
    }

    private fun setRecyclerView() {
        noteListAdapter = NoteListAdapter()
        with(viewBinding!!) {
            list.adapter = noteListAdapter
            list.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )
        }
        setClickListener()
        setSwipeListener()
    }


    private fun setClickListener() {
        noteListAdapter.onNoteItemClickListener = {
            findImplementationOrThrow<FragmentNavigator>()
                .navigateTo(
                    NoteDetailsFragment.newInstanceEditItem(it.id),
                    NoteDetailsFragment.NAME
                )
        }
    }

    private fun setSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noteListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNoteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(viewBinding!!.list)
    }


    companion object {

        fun newInstance() = NoteListFragment()
    }

}
