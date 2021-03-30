package com.example.retrofictbootcamp.utils

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "results")
    val resultsList: List<AnimeResult> = emptyList()
)
