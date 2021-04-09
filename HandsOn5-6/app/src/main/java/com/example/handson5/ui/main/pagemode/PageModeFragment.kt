package com.example.handson5.ui.main.pagemode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.handson5.R
import com.example.handson5.data.entity.Movie
import com.example.handson5.ui.main.pagemode.PageModeActivityAdapter.Companion.MOVIE

class PageModeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_movie_details, container, false)
        arguments?.get(MOVIE)?.let {
            populateView(view, it as Movie)
        }
        return view
    }

    private fun populateView(view: View, movie: Movie) {
        view.let {
            val image = it.findViewById<AppCompatImageView>(R.id.image)
            Glide.with(this).load(movie.image).into(image)
            it.findViewById<TextView>(R.id.title).text = movie.title
            it.findViewById<TextView>(R.id.description).text = movie.description
            it.findViewById<AppCompatButton>(R.id.downloadBtn).visibility = View.GONE
        }
    }
}