package com.org.jona.kmpmovies.di

import com.org.jona.kmpmovies.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
}
