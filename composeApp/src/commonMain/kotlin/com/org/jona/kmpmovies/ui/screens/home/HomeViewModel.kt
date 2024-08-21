package com.org.jona.kmpmovies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.jona.kmpmovies.ui.screens.UIState
import com.org.jona.kmpmovies.ui.screens.data.MovieService
import com.org.jona.kmpmovies.ui.screens.data.MoviesRemoteToMoviesDomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesService: MovieService,
    private val mapper: MoviesRemoteToMoviesDomainMapper,
) : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { UIState.Loading }
            _state.update {
                UIState.Success(
                    movies = moviesService.fetchPopularMovies().results.map(mapper)
                    //movies = moviesService.fetchPopularMovies().results.map { mapper(it) }
                )
            }
        }
    }

}