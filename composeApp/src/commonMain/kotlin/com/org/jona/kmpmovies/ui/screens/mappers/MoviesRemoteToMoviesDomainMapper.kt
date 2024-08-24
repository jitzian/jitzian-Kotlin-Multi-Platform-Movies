package com.org.jona.kmpmovies.ui.screens.mappers

import com.org.jona.kmpmovies.ui.screens.data.Movie
import com.org.jona.kmpmovies.ui.screens.detail.RemoteMovie

class MoviesRemoteToMoviesDomainMapper : (RemoteMovie) -> (Movie) {
    override fun invoke(remoteMovie: RemoteMovie) = with(remoteMovie) {
        Movie(
            id = id,
            title = title,
            poster = "https://image.tmdb.org/t/p/w500$posterPath"
        )
    }
}