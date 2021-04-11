package com.example.recyclerview.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView = view.findViewById(R.id.title)
}