package com.notes.di

import android.app.Application
import android.content.Context
import com.notes.data.database.NoteDao
import com.notes.data.database.NoteDatabase
import com.notes.data.repository.NoteRepositoryImpl
import com.notes.domain.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun provideNoteRepository(impl: NoteRepositoryImpl): NoteRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideNoteDao(
            application: Application
        ): NoteDao {
            return NoteDatabase.getInstance(application).noteDao()
        }
    }
}