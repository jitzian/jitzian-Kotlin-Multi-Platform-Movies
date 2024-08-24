package com.org.jona.kmpmovies.ui.screens.data

import com.org.jona.kmpmovies.ui.screens.detail.RemoteMovie
import com.org.jona.kmpmovies.ui.screens.detail.RemoteResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val client: HttpClient,
) {
    suspend fun fetchPopularMovies(): RemoteResult =
        client.get("/3/discover/movie?sort_by=popularity.desc")
            .body<RemoteResult>()

    suspend fun fetchMovieById(id: Int): RemoteMovie =
        client.get("/3/movie/$id")
            .body<RemoteMovie>()

}