package com.example.handson5.ui.main.pagemode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.handson5.data.entity.Movie

class PageModeActivityAdapter(activity: AppCompatActivity, val total: MutableList<Movie>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return total.size
    }

    override fun createFragment(position: Int): Fragment {
        return PageModeFragment().apply{
            val bundle = Bundle()
            bundle.putSerializable(MOVIE, total[position])
            arguments = bundle
        }
    }

    companion object{
        const val MOVIE = "movie"
    }
}