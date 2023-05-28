package com.nzh.movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nzh.movies.remote.UpComingMovie

@Dao
interface UpComingMoviesDao {
    @Query("SELECT * FROM UpComingMovie")
    fun allUpComingMovies():List<UpComingMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpComingMovies(upComingMovie: UpComingMovie)

    @Query("DELETE FROM UpComingMovie")
    fun deleteAll()

    @Update
    suspend fun updateFavourite(movie: UpComingMovie)
}
