package com.goodayedi.asteroid

import android.app.Application
import com.goodayedi.asteroid.repository.AsteroidRepository
import timber.log.Timber

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}