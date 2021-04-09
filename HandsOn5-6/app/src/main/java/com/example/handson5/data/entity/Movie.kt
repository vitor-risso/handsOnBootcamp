package com.example.handson5.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "description")
    val description: String = "",

    @Json(name = "image")
    val image: String ="",

    @Json(name = "title")
    val title: String = ""
): Serializable
