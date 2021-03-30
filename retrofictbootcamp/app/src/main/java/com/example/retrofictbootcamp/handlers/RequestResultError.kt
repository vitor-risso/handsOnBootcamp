package com.example.retrofictbootcamp.handlers

open class RequestResultError<T>(val msg: String) : RequestResults<T>()
