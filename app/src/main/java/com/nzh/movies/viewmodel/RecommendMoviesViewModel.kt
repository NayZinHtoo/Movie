package com.nzh.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nzh.movies.remote.GetMoviesResponse
import com.nzh.movies.repository.RecommendedMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject


@HiltViewModel
class RecommendMoviesViewModel @Inject constructor(var movieRepository: RecommendedMoviesRepository):
    BaseViewModel() {
    init {
        super.initViewModel()
    }
    lateinit var disposable: Disposable
    val mResponseRecommendedMoviesLD: MutableLiveData<GetMoviesResponse> = movieRepository.mResponseRecommendedMoviesLD

    fun getRecommendedMovies(page:Int) {
        movieRepository.getUpRecommendedMovies(page)
    }
}