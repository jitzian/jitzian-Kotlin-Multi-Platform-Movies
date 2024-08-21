package com.org.jona.kmpmovies.ui.screens.home

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.org.jona.kmpmovies.ui.screens.data.MovieService
import com.org.jona.kmpmovies.ui.screens.data.MoviesRemoteToMoviesDomainMapper
import com.org.jona.kmpmovies.ui.screens.data.movies
import com.org.jona.kmpmovies.ui.screens.detail.DetailScreen
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    //TODO: Optimize this. I gotta move to a different layer with DI
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    val apiKey = stringResource(Res.string.api_key)
    val viewModel = viewModel {
        HomeViewModel(
            moviesService = MovieService(apiKey, client),
            mapper = MoviesRemoteToMoviesDomainMapper()
        )
    }

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
                vm = viewModel
            )
        }
        composable(
            route = "details/{movieId}",
            arguments = listOf(
                (navArgument("movieId") { type = NavType.IntType })
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                movies.first { it.id == movieId },
                onBack = { navController.popBackStack() }
            )
        }
    }
}