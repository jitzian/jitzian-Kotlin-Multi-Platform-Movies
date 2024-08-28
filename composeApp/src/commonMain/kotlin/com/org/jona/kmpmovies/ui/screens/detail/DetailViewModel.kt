package com.org.jona.kmpmovies.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.jona.kmpmovies.ui.screens.data.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class DetailViewModel(
    private val id: Int,
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UIState.Loading
            delay(1.seconds)
            _state.value = UIState.Success(moviesRepository.fetchMovieById(id))
        }
    }
}