package com.squarecodec.movies.di

import com.squarecodec.movies.data.remote.MovieService
import com.squarecodec.movies.data.remote.RemoteDataSource
import com.squarecodec.movies.data.repository.MovieRepositoryImpl
import com.squarecodec.movies.domain.repository.MovieRepository
import com.squarecodec.movies.domain.usecase.GetMovieUseCase
import com.squarecodec.movies.domain.usecase.GetMoviesUseCase
import com.squarecodec.movies.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val sharedModules = listOf(dataModule, utilityModule, domainModule)

fun getSharedModules() = sharedModules