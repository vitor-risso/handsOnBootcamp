package com.example.handson5.ui.main

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.handson5.R

class TitleDescriptionImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: AppCompatImageView = view.findViewById(R.id.image)
    val title: TextView = view.findViewById(R.id.title)
    val description: TextView = view.findViewById(R.id.description)
    val container: ConstraintLayout = view.findViewById<ConstraintLayout>(R.id.container)
}