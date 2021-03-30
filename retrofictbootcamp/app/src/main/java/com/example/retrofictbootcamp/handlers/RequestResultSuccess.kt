package com.example.retrofictbootcamp.handlers

data class RequestResultSuccess<T>(val data: T) : RequestResults<T>()
