package com.notes.di

import android.app.Application
import com.notes.presentation.details.NoteDetailsFragment
import com.notes.presentation.list.NoteListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: NoteListFragment)

    fun inject(fragment: NoteDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
