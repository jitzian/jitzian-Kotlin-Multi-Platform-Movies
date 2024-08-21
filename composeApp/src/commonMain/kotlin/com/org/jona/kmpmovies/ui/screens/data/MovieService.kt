package com.org.jona.kmpmovies.ui.screens.data

import com.org.jona.kmpmovies.ui.screens.detail.RemoteResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val apiKey: String,
    private val client: HttpClient,
) {
    suspend fun fetchPopularMovies(): RemoteResult =
        client.get("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=$apiKey")
            .body<RemoteResult>()

}