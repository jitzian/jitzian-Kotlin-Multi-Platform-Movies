package com.org.jona.kmpmovies

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDao
import com.org.jona.kmpmovies.ui.screens.home.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(moviesDao: MoviesDao) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    Navigation(moviesDao = moviesDao)
}

