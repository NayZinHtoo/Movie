package com.nzh.movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nzh.movies.remote.Movie

@Dao
interface RecommendMoviesDao {
    @Query("SELECT * FROM Movie")
    fun allRecommendMovies():List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendMovies(recommend:Movie)

    @Query("DELETE FROM Movie")
    fun deleteAll()

    @Update
    suspend fun updateFavourite(movie: Movie)
}