package com.example.retrofictbootcamp.extensions

import com.example.retrofictbootcamp.handlers.RequestResultError
import com.example.retrofictbootcamp.handlers.RequestResultNotFound
import com.example.retrofictbootcamp.handlers.RequestResultSuccess
import com.example.retrofictbootcamp.handlers.RequestResults
import retrofit2.Call
import retrofit2.awaitResponse

// Extension method to handle errors and avoid exceptions with custom types.
suspend fun <T> Call<T>.toRequestResult(): RequestResults<T> {
    val response = this.awaitResponse()

    return if (response.isSuccessful) {
        // If the request runs with success, his data is returned.
        RequestResultSuccess(response.body()!!)
    } else {
        if (response.code() == 404) {
            // If the request returns 404 status, a custom not found type is returned.
            RequestResultNotFound()
        } else {
            // And if the request returns an unexpected error, a generic error type is returned.
            RequestResultError(response.code().toString() + ": " + response.message())
        }
    }
}

