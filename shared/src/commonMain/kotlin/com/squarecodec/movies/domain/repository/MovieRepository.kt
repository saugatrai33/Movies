package com.squarecodec.movies.domain.repository

import com.squarecodec.movies.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId: Int): Movie
}