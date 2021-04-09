package com.example.handson5.util

import com.example.handson5.data.entity.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @Expose
    @SerializedName("movies")
    var movies: List<Movie>? = null
}