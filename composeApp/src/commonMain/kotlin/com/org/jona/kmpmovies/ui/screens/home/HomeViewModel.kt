package com.org.jona.kmpmovies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.jona.kmpmovies.ui.screens.data.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UIState.Loading
            //delay(1.seconds)
            //_state.value = UIState.Success(moviesRepository.fetchPopularMovies())
            //
            moviesRepository.movies.collect {
                _state.value = UIState.Success(it)
            }
        }
    }

}