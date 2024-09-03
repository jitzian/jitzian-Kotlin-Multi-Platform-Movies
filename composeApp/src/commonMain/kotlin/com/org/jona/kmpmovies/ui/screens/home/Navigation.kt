package com.org.jona.kmpmovies.ui.screens.home

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.org.jona.kmpmovies.ui.screens.data.MovieService
import com.org.jona.kmpmovies.ui.screens.data.MoviesRepository
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDao
import com.org.jona.kmpmovies.ui.screens.detail.DetailScreen
import com.org.jona.kmpmovies.ui.screens.detail.DetailViewModel
import com.org.jona.kmpmovies.ui.screens.mappers.MoviesRemoteToMoviesDomainMapper
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    moviesDao: MoviesDao,
) {
    val navController = rememberNavController()
    val repository = rememberMoviesRepository(moviesDao)

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate("details/${movie.id}")
                },
                vm = HomeViewModel(moviesRepository = repository)
            )
        }
        composable(
            route = "details/{movieId}",
            arguments = listOf(
                (navArgument("movieId") { type = NavType.IntType })
            )
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                vm = DetailViewModel(movieId, repository),
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun rememberMoviesRepository(
    moviesDao: MoviesDao,
    apiKey: String = stringResource(Res.string.api_key)
): MoviesRepository = remember {

    //TODO: Optimize this. I gotta move to a different layer with DI
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)

            }
        }
    }

    //This is the returned value. This way we avoid the return type of the remember block
    MoviesRepository(MovieService(client), MoviesRemoteToMoviesDomainMapper(), moviesDao)
}