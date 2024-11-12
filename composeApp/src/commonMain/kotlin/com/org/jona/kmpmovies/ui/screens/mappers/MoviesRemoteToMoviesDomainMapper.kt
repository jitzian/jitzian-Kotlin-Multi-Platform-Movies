package com.org.jona.kmpmovies.ui.screens.mappers

import com.org.jona.kmpmovies.ui.screens.data.Movie
import com.org.jona.kmpmovies.ui.screens.detail.RemoteMovie

class MoviesRemoteToMoviesDomainMapper : (RemoteMovie) -> (Movie) {
    override fun invoke(remoteMovie: RemoteMovie) = with(remoteMovie) {
        Movie(
            id = id,
            title = title,
            poster = "https://image.tmdb.org/t/p/w500$posterPath",
            backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" },
            overview = overview,
            releaseDate = releaseDate,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            popularity = popularity,
            voteAverage = voteAverage,
            isFavorite = false,
        )
    }
}