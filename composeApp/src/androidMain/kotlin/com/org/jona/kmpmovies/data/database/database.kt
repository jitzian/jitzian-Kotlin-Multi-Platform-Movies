package com.org.jona.kmpmovies.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.org.jona.kmpmovies.ui.screens.data.database.DATABASE_NAME
import com.org.jona.kmpmovies.ui.screens.data.database.MoviesDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MoviesDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getFileStreamPath(DATABASE_NAME)
    return Room.databaseBuilder(
        appContext,
        MoviesDatabase::class.java, dbFile.absolutePath
    )
}