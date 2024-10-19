package com.org.jona.kmpmovies

import android.app.Application
import com.org.jona.kmpmovies.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            //To use context wherever is required
            androidContext(this@MoviesApp)
        }
    }
}