package com.org.jona.kmpmovies.di

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.org.jona.kmpmovies.data.AndroidRegionDataSource
import com.org.jona.kmpmovies.data.database.getDatabaseBuilder
import com.org.jona.kmpmovies.ui.screens.data.RegionDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
    factory { Geocoder(get()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
    factory { LocationServices.getFusedLocationProviderClient(androidApplication()) }

}
