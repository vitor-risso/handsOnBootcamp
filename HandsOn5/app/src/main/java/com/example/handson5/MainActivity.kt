package com.example.handson5

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.handson5.data.repository.MovieRepository
import com.example.handson5.databinding.ActivityMainBinding
import com.example.handson5.ui.main.MainViewModel
import com.example.handson5.ui.main.MovieAdapter
import com.example.handson5.util.Movie

class MainActivity : AppCompatActivity(), MovieAdapter.MovieListener {
    lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        movieAdapter = MovieAdapter(listOf(), this)

        observeViewModel()
        addClickListener()

    }

    private fun observeViewModel() {
        viewModel.showLoadingLiveData.observe(this, {
            binding.progressBar.visibility = View.VISIBLE
        })

        viewModel.hideLoadingLiveData.observe(this, {
            binding.progressBar.visibility = View.GONE
        })

        viewModel.showErrorMessageLiveData.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT)
        })
    }

    private fun addClickListener() {
        binding.recyclerView.adapter = movieAdapter


        binding.loadMoviesBtn.setOnClickListener {
            viewModel.setIsLoading(true)

            viewModel.searchMovies()
        }

        binding.eraseMoviesBtn.setOnClickListener {
            viewModel.setIsLoading(false)
        }
    }

    override fun onSelectItem(movie: Movie) {

    }
}