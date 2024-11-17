package com.org.jona.kmpmovies.ui.screens.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieService(
    private val client: HttpClient,
) {
    suspend fun fetchPopularMovies(region: String): RemoteResult =
        //client.get("/3/discover/movie?sort_by=popularity.desc")
        client.get("/3/discover/movie") {
            parameter("sort_by", "popularity.desc")
            parameter("region", region)
        }.body<RemoteResult>()

    suspend fun fetchMovieById(id: Int): RemoteMovie =
        client.get("/3/movie/$id")
            .body<RemoteMovie>()

}