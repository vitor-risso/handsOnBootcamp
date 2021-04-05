package com.example.handson5.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handson5.data.repository.MovieDataSource
import com.example.handson5.data.repository.MovieRepository
import com.example.handson5.util.Movie
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(), MovieDataSource.LoadMoviesCallback {
    private val _showLoadingLiveData = MutableLiveData<Unit>()
    val showLoadingLiveData: LiveData<Unit>
        get() = _showLoadingLiveData

    private val _hideLoadingLiveData = MutableLiveData<Unit>()
    val hideLoadingLiveData: LiveData<Unit>
        get() = _hideLoadingLiveData


    private val _navigateToDetailsLiveData = MutableLiveData<Movie>()
    val navigateToDetailsLiveData: LiveData<Movie>
        get() = _navigateToDetailsLiveData


    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = _moviesLiveData

    private val _showErrorMessageLiveData = MutableLiveData<String>()
    val showErrorMessageLiveData: LiveData<String>
        get() = _showErrorMessageLiveData

    fun setIsLoading(loading: Boolean) {
        if (loading) {
            _showLoadingLiveData.postValue(Unit)
        } else {
            _hideLoadingLiveData.postValue(Unit)
        }
    }

    fun searchMovies(){
        val callback = this

        viewModelScope.launch {
            MovieRepository().getMovies(callback)
        }

    }

    override fun onMoviesLoaded(movies: List<Movie>) {
        _moviesLiveData.postValue(movies)
        setIsLoading(false)
    }


    override fun onDataNotAvailable() {
        _showErrorMessageLiveData.postValue("Nada encontrado")
        setIsLoading(false)

    }

    override fun onError() {
        _showErrorMessageLiveData.postValue("Eror na API")
        setIsLoading(false)
    }
}
