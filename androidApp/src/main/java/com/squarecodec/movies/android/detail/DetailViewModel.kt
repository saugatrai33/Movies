package com.squarecodec.movies.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squarecodec.movies.domain.model.Movie
import com.squarecodec.movies.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    movieId: Int
) : ViewModel() {

    init {
        loadMovie(movieId)
    }

    var uiState by mutableStateOf(DetailScreenState())

    private fun loadMovie(movieId: Int) {
        if (uiState.loading) return

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val movie = getMovieUseCase.invoke(id = movieId)
                uiState.copy(loading = false, movie = movie)
            } catch (exception: Exception) {
                uiState.copy(
                    loading = false,
                    errorMessage = "could not load movie: ${exception.localizedMessage}"
                )
            }
        }
    }

}

data class DetailScreenState(
    var loading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null
)