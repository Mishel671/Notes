package com.notes.di

import androidx.lifecycle.ViewModel
import com.notes.di.ViewModelKey
import com.notes.presentation.list.NoteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    fun bindMainViewModel(viewModel: NoteListViewModel): ViewModel

}
