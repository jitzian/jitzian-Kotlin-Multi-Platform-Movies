package com.org.jona.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import com.org.jona.kmpmovies.data.database.getDatabaseBuilder
import com.org.jona.kmpmovies.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}