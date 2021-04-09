package com.example.handson5.ui.main.pagemode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.handson5.R
import com.example.handson5.data.entity.Movie

class PageModeActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var movie: MutableList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_mode)

        viewPager = findViewById(R.id.viewPager)
        movie = intent.getSerializableExtra(MOVIES) as MutableList<Movie>


        viewPager.adapter = PageModeActivityAdapter(this, movie)

    }

    companion object{
        const val MOVIES = "movies"
    }
}