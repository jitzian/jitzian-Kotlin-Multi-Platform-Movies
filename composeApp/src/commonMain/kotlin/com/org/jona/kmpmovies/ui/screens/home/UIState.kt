package com.org.jona.kmpmovies.ui.screens.home

import com.org.jona.kmpmovies.ui.screens.data.Movie

sealed interface UIState {
    data object Loading : UIState
    data object Empty : UIState
    data class Error(val message: String) : UIState
    data class Success(
        val movies: List<Movie> = emptyList()
    ) : UIState
}