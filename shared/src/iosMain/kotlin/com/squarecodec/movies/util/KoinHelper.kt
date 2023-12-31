package com.squarecodec.movies.util

import com.squarecodec.movies.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getSharedModules())
    }
}