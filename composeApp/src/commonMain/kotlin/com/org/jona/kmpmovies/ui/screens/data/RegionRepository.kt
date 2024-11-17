package com.org.jona.kmpmovies.ui.screens.data

interface RegionDataSource {
    suspend fun fetchRegion(): String
}

const val DEFAULT_REGION = "US"

class RegionRepository (
    private val regionDataSource: RegionDataSource
){
    //TODO: Implement the logic. This is temporary
    suspend fun fetchRegion() = regionDataSource.fetchRegion()

}