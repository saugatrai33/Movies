package com.squarecodec.movies.data.repository

import com.squarecodec.movies.data.remote.RemoteDataSource
import com.squarecodec.movies.data.remote.util.toMovie
import com.squarecodec.movies.domain.model.Movie
import com.squarecodec.movies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page = page).results.map { it.toMovie() }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDataSource.getMovie(movieId = movieId).toMovie()
    }
}