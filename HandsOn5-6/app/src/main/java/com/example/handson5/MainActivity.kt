package com.example.handson5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.handson5.databinding.ActivityMainBinding
import com.example.handson5.ui.main.MainViewModel
import com.example.handson5.ui.main.MovieAdapter
import com.example.handson5.data.entity.Movie
import com.example.handson5.ui.main.MovieDetailsActivity
import com.example.handson5.ui.main.pagemode.PageModeActivity

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

        viewModel.moviesLiveData.observe(this, { movies ->
            movieAdapter.updateList(movies)
        })
    }

    private fun addClickListener() {
        binding.recyclerView.adapter = movieAdapter


        binding.loadMoviesBtn.setOnClickListener {
            viewModel.setIsLoading(true)
            viewModel.searchMovies()
            binding.pageModeBtn.isEnabled = true
        }

        binding.eraseMoviesBtn.setOnClickListener {
            viewModel.setIsLoading(false)
            movieAdapter.updateList(emptyList())
	   binding.pageModeBtn.isEnabled = false

        }

        binding.pageModeBtn.setOnClickListener {
            val intent = Intent(this, PageModeActivity::class.java).apply {
               putExtra(PageModeActivity.MOVIES, movieAdapter.getList() as ArrayList)
            }


            startActivity(intent)
        }

    }

    override fun onSelectItem(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra("title", movie.title)
            putExtra("description", movie.description)
            putExtra("image", movie.image)
        }

        startActivity(intent)

    }
}
