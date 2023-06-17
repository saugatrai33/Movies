package com.squarecodec.movies.data.remote.util

import com.squarecodec.movies.data.remote.MovieRemote
import com.squarecodec.movies.domain.model.Movie

internal fun MovieRemote.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        posterImage = imageUrl(posterImage),
        releaseDate = releaseDate
    )
}


private fun imageUrl(path: String) = "https://image.tmdb.org//t/p/w500/$path"