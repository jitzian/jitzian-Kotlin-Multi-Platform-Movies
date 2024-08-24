package com.org.jona.kmpmovies.ui.screens.detail

import com.org.jona.kmpmovies.ui.screens.data.Movie

sealed interface UIState {

    data object Empty : UIState
    data object Loading : UIState
    data class Success(val movie: Movie) : UIState
    data class Error(val message: String) : UIState

}