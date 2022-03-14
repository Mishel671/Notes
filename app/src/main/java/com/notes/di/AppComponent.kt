package com.notes.di

import android.app.Application
import com.notes.presentation.list.NoteListFragment
import dagger.*

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: NoteListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
