package com.example.handson5.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "descripition")
    val descripition: String = "",

    @Json(name = "image")
    val image: String ="",

    @Json(name = "title")
    val title: String = ""
)
