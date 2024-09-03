package com.org.jona.kmpmovies.ui.screens.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.org.jona.kmpmovies.ui.screens.data.Movie
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

const val DATABASE_NAME = "movies.db"

// HACK: Room DB throws an error if we don't provide an empty implementation for clearAllTables
interface DB {
    fun clearAllTables() {}
}

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase(), DB {
    abstract fun moviesDao(): MoviesDao

    // Leave this empty to allow the room DB to succeed
    override fun clearAllTables() {}
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<MoviesDatabase>
): MoviesDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .build()
}