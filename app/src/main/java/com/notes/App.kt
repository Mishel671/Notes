package com.notes

import android.app.Application
import com.notes.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }


}