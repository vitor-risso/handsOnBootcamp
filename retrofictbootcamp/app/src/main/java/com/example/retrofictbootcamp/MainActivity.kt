package com.example.retrofictbootcamp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofictbootcamp.extensions.toRequestResult
import com.example.retrofictbootcamp.handlers.RequestResultError
import com.example.retrofictbootcamp.handlers.RequestResultNotFound
import com.example.retrofictbootcamp.handlers.RequestResultSuccess
import com.example.retrofictbootcamp.interceptor.LogInterceptor
import com.example.retrofictbootcamp.utils.SearchService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var searchService: SearchService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchService = buildSearchService()

        val searchButton = findViewById<Button>(R.id.buttonSearch)

        searchButton.setOnClickListener {
            dispatchRequestAnimes()
        }
    }

    private fun dispatchRequestAnimes() {
        //Open a new thread to avoid UI freeze
        CoroutineScope(Dispatchers.Main).launch { requestAnimes() }
    }


    private fun buildSearchService(): SearchService {
        val moshi = Moshi.Builder().build()
        val client = OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

        return retrofit.create(SearchService::class.java)
    }

    private suspend fun requestAnimes() {
        val text = findViewById<EditText>(R.id.search).text.toString()

        when (val foundAnimes = searchService.search(text).toRequestResult()) {
            is RequestResultSuccess -> {
                // Parse the result and put it inside a TextView.
                var result = ""
                foundAnimes.data.resultsList.forEach {
                    result += it.title + "\n" + it.synopsis + "\n\n\n"
                }

                findViewById<TextView>(R.id.textView).text = result
            }

            is RequestResultNotFound -> {
                findViewById<TextView>(R.id.textView).text =
                    "Cannot find any anime with the provided text."
            }

            is RequestResultError -> {
                findViewById<TextView>(R.id.textView).text = foundAnimes.msg
            }
        }

    }
}