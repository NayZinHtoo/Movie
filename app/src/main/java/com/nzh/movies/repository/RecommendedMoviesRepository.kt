package com.nzh.movies.repository

import androidx.lifecycle.MutableLiveData
import com.nzh.movies.interfaces.BaseModel
import com.nzh.movies.remote.GetMoviesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class RecommendedMoviesRepository @Inject constructor() {

    val mResponseRecommendedMoviesLD: MutableLiveData<GetMoviesResponse> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    fun getUpRecommendedMovies(page: Int) {
        BaseModel.api.getPopularMovies(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mResponseRecommendedMoviesLD.value = it

                }, {
                    mErrorLD.value = it.localizedMessage
                }
            )

    }

}