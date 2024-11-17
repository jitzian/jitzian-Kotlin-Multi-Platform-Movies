package com.org.jona.kmpmovies.ui.screens.home

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.org.jona.kmpmovies.ui.screens.detail.DetailScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

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
                //vm = HomeViewModel(moviesRepository = repository)

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
                //vm = DetailViewModel(movieId, repository),
                vm = koinViewModel(parameters = { parametersOf(movieId) }),
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/*
@Composable
private fun rememberMoviesRepository(moviesDao: MoviesDao): MoviesRepository = remember {

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
                parameters.append("api_key", BuildConfig.API_KEY)

            }
        }
    }

    //This is the returned value. This way we avoid the return type of the remember block
    MoviesRepository(MovieService(client), MoviesRemoteToMoviesDomainMapper(), moviesDao)
}*/
