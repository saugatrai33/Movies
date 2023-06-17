package com.squarecodec.movies.android

import android.app.Application
import com.squarecodec.movies.android.di.appModule
import com.squarecodec.movies.di.getSharedModules
import org.koin.core.context.startKoin

class Movie : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}