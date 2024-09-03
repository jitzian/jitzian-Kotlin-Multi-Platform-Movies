package com.org.jona.kmpmovies.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.org.jona.kmpmovies.ui.screens.data.database.DATABASE_NAME
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDatabase
import com.org.jona.kmpmovies.ui.screens.data.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFilePath,
        factory =  { MoviesDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
}