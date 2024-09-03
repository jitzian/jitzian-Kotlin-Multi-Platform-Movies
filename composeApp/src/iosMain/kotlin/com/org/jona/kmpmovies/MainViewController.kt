package com.org.jona.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import com.org.jona.kmpmovies.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuilder().build()
    App(database.moviesDao())
}