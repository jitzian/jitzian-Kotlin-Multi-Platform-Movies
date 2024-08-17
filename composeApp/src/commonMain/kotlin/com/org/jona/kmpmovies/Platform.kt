package com.org.jona.kmpmovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform