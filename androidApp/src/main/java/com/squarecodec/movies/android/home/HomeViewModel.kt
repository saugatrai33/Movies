package com.squarecodec.movies.android.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squarecodec.movies.domain.model.Movie
import com.squarecodec.movies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    init {
        loadMovies(forceLoad = false)
    }

    fun loadMovies(forceLoad: Boolean = false) {
        if (uiState.loading) return
        if (forceLoad) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            try {
                val resultMovies = getMoviesUseCase(currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies
                Log.d(
                    TAG,
                    "loadMovies: movie size ----> ${movies.size}, current page --->> $currentPage"
                )
                currentPage += 1
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isEmpty(),
                    movies = movies
                )
            } catch (exception: Exception) {
                Log.d(TAG, "loadMovies: fetch movies error ---> ${exception.localizedMessage}")
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "could not load movies: ${exception.localizedMessage}"
                )
            }

        }
    }
}

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)