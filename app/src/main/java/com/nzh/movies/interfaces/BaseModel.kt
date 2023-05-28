package com.nzh.movies.interfaces

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseModel {

    companion object {
        var api: Api

        init {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
            okHttpClient.readTimeout(30, TimeUnit.SECONDS)
            okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
            okHttpClient.pingInterval(3,TimeUnit.SECONDS)
            okHttpClient.addInterceptor(loggingInterceptor)

            val retrofitService = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            api = retrofitService.create(Api::class.java)

        }
    }
}