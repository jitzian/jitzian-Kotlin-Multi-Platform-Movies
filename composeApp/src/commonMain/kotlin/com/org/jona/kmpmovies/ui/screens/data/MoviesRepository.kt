package com.org.jona.kmpmovies.ui.screens.data

import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDao
import com.org.jona.kmpmovies.ui.screens.data.remote.MovieService
import com.org.jona.kmpmovies.ui.screens.mappers.MoviesRemoteToMoviesDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val moviesService: MovieService,
    private val mapper: MoviesRemoteToMoviesDomainMapper,
    private val moviesDao: MoviesDao,
    private val regionRepository: RegionRepository,
) {

    /*suspend fun fetchPopularMovies() =
        moviesService.fetchPopularMovies().results.map { mapper(it) }*/

    val movies: Flow<List<Movie>> = moviesDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val popularMovies = moviesService.fetchPopularMovies(regionRepository.fetchRegion()).results.map { mapper(it) }
            moviesDao.save(popularMovies)
        }
    }

    //suspend fun fetchMovieById(id: Int): Movie = mapper(moviesService.fetchMovieById(id))
    fun fetchMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).onEach { movie ->
            if (movie == null) {
                val remoteMovie = mapper(moviesService.fetchMovieById(id))
                moviesDao.save(listOf(remoteMovie))
            }
        }

    suspend fun toggleFavorite(movie: Movie) =
        moviesDao.save(listOf(movie.copy(isFavorite = !movie.isFavorite)))


}