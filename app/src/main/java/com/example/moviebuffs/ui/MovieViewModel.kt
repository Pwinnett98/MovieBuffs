package com.example.moviebuffs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebuffs.network.MovieApi
import com.example.moviebuffs.network.Movies
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.moviebuffs.ui.utils.MovieContentType


sealed interface MovieUiState {
    data class Success(val movies: List<Movies>) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState

}

class MovieViewModel : ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {
        getMoviePhotos()
    }

    /**
     * [MoviePhoto] [List] [MutableList].
     */
    fun getMoviePhotos() {
        viewModelScope.launch {
            movieUiState = try {
                MovieUiState.Success(MovieApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    fun updateCurrentMovie(selectedMovie: Movies) {
        _uiState.update {
            it.copy(currentMovie = selectedMovie)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

