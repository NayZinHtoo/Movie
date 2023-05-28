package com.nzh.movies.interfaces

import com.nzh.movies.remote.GetMoviesResponse
import com.nzh.movies.remote.GetUpComingMoviesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "6e63768845f3e505da31f7f10b21855d",
        @Query("page") page: Int
    ): Observable<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("api_key") apiKey: String = "6e63768845f3e505da31f7f10b21855d",
        @Query("page") page: Int
    ): Observable<GetUpComingMoviesResponse>
}