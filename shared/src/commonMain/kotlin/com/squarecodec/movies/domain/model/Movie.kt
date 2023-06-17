package com.squarecodec.movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val posterImage: String,
    val releaseDate: String,
)
