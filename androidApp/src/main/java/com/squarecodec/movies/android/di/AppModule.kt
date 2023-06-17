package com.squarecodec.movies.android.di

import com.squarecodec.movies.android.detail.DetailViewModel
import com.squarecodec.movies.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}