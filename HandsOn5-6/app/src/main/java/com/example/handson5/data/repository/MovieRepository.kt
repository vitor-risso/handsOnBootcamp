package com.example.handson5.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository : MovieDataSource {
    private lateinit var mMovieApi: MovieApi

    init {
        createApi()
    }

    private fun createApi() {
        val mRetrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL)
                .build()
        mMovieApi = mRetrofit.create(MovieApi::class.java)
    }

    override suspend fun getMovies(callback: MovieDataSource.LoadMoviesCallback?): Unit =
        withContext(Dispatchers.IO) {

            val res = mMovieApi.getMovies().execute()

            if (res.isSuccessful) {
                res.body()?.movies?.let {
                    callback?.onMoviesLoaded(it)
                } ?: run {
                    callback?.onDataNotAvailable()
                }
            } else {
                callback?.onError()
            }
        }

    companion object {
        var URL = "https://demo2458961.mockable.io"
    }
}