package com.example.retrofictbootcamp.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(chain.request().url().toString(), "Requesting")

        return chain.proceed(chain.request())
    }
}