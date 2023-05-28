package com.nzh.movies.remote

import com.google.gson.annotations.SerializedName

data class GetUpComingMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<UpComingMovie>,
    @SerializedName("total_pages") val pages: Int
)