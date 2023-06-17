package com.squarecodec.movies.data.remote

import com.squarecodec.movies.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {
    suspend fun getMovies(page: Int) = withContext(dispatcher.io) {
        return@withContext apiService.getMovies(page = page)
    }

    suspend fun getMovie(movieId: Int) = withContext(dispatcher.io) {
        return@withContext apiService.getMovie(movieId = movieId)
    }
}