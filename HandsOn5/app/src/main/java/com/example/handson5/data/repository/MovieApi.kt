package com.example.handson5.data.repository

import com.example.handson5.util.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("movies/")
    fun getMovies(): Call<MovieResponse>
}