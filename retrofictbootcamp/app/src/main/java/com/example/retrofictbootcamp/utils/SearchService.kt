package com.example.retrofictbootcamp.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/anime")
    fun search(@Query("q") search : String): Call<SearchResult>
}