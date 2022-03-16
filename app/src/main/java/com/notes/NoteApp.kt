package com.notes

import android.app.Application
import com.notes.di.DaggerApplicationComponent

class NoteApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}