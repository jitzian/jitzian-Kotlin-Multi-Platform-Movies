package com.org.jona.kmpmovies.di

import com.org.jona.kmpmovies.data.IosRegionDataSource
import com.org.jona.kmpmovies.data.database.getDatabaseBuilder
import com.org.jona.kmpmovies.ui.screens.data.RegionDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
    factoryOf(::IosRegionDataSource) bind RegionDataSource::class
}
