package com.nzh.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nzh.movies.remote.GetUpComingMoviesResponse
import com.nzh.movies.repository.UpComingMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class UpCommingMoviesModel @Inject constructor(var movieRepository: UpComingMoviesRepository):
    BaseViewModel() {
    init {
        super.initViewModel()
    }
    lateinit var disposable: Disposable
    val mResponseUpComingMoviesLD: MutableLiveData<GetUpComingMoviesResponse> = movieRepository.mResponseUpComingMoviesLD

    fun getUpComingMovies(page:Int) {
        movieRepository.getUpComingMovies(page)
    }
}