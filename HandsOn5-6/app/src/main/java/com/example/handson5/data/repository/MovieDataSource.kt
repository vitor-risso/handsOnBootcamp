package com.example.handson5.data.repository

import com.example.handson5.data.entity.Movie

interface MovieDataSource {
    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: List<Movie>)
        fun onDataNotAvailable()
        fun onError()
    }

    suspend fun getMovies(callback: LoadMoviesCallback?)
}