package com.org.jona.kmpmovies.ui.screens.data

import com.org.jona.kmpmovies.ui.screens.mappers.MoviesRemoteToMoviesDomainMapper

class MoviesRepository(
    private val moviesService: MovieService,
    private val mapper: MoviesRemoteToMoviesDomainMapper,
) {
    suspend fun fetchPopularMovies() =
        moviesService.fetchPopularMovies().results.map { mapper(it) }

    suspend fun fetchMovieById(id: Int): Movie = mapper(moviesService.fetchMovieById(id))
}