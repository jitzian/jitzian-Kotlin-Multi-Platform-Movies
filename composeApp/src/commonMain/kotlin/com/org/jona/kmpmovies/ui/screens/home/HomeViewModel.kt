package com.org.jona.kmpmovies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.jona.kmpmovies.movies
import com.org.jona.kmpmovies.ui.screens.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeViewModel : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { UIState.Loading }
            delay(2.seconds)
            _state.update {
                UIState.Success(
                    movies = movies
                )
            }
        }
    }

}