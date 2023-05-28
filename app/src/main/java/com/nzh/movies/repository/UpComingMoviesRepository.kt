package com.nzh.movies.repository

import androidx.lifecycle.MutableLiveData
import com.nzh.movies.interfaces.BaseModel
import com.nzh.movies.remote.GetUpComingMoviesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UpComingMoviesRepository @Inject constructor() {

    val mResponseUpComingMoviesLD: MutableLiveData<GetUpComingMoviesResponse> = MutableLiveData()
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    fun getUpComingMovies(page: Int) {
        BaseModel.api.getUpComingMovies(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mResponseUpComingMoviesLD.value = it

                }, {
                    mErrorLD.value = it.localizedMessage
                }
            )

    }

}