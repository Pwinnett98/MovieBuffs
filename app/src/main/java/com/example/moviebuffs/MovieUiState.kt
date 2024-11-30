package com.example.moviebuffs

import com.example.moviebuffs.network.Movies

class MovieUiState {
    val MovieList: List<Movies> = emptyList(),
    val currentMovie: Movies = MoviesDataSource.defaultMovie,
    val isShowingListPage: Boolean = true
}