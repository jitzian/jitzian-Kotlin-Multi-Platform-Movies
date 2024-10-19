package com.org.jona.kmpmovies.di

import androidx.room.RoomDatabase
import com.org.jona.kmpmovies.BuildConfig
import com.org.jona.kmpmovies.ui.screens.data.MovieService
import com.org.jona.kmpmovies.ui.screens.data.MoviesRepository
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDao
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDatabase
import com.org.jona.kmpmovies.ui.screens.detail.DetailViewModel
import com.org.jona.kmpmovies.ui.screens.home.HomeViewModel
import com.org.jona.kmpmovies.ui.screens.mappers.MoviesRemoteToMoviesDomainMapper
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<MoviesDao> {
        val dbBuilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBuilder.build().moviesDao()
    }
}

val dataModule = module {
    factoryOf(::MoviesRepository)
    factoryOf(::MovieService)
    single<HttpClient> {
        HttpClient {
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
    }
}

val mappersModule = module {
    factoryOf(::MoviesRemoteToMoviesDomainMapper)
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}


expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule, mappersModule)
    }
}