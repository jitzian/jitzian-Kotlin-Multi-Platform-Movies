package com.org.jona.kmpmovies.ui.screens.home

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.org.jona.kmpmovies.movies
import com.org.jona.kmpmovies.ui.screens.detail.DetailScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
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
                }
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