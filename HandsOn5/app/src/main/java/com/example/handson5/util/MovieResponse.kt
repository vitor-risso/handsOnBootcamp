package com.example.handson5.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @Expose
    @SerializedName("movies")
    var movies: List<Movie>? = null
}