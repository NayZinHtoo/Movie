package com.nzh.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nzh.movies.remote.Movie
import com.nzh.movies.remote.UpComingMovie

@Database(entities = [(Movie::class),(UpComingMovie::class)], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun recommendMoviesDao(): RecommendMoviesDao
    abstract fun upComingMoviesDao(): UpComingMoviesDao
}