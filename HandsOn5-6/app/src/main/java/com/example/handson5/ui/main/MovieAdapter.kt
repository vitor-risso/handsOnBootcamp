package com.example.handson5.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.handson5.R
import com.example.handson5.data.entity.Movie

class MovieAdapter(initialItems: List<Movie>, private val movieListener: MovieListener) :
    RecyclerView.Adapter<TitleDescriptionImageViewHolder>() {

    private var items: List<Movie> = initialItems


    override fun getItemCount(): Int = items.size

    interface MovieListener{
        fun onSelectItem(movie: Movie)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TitleDescriptionImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return TitleDescriptionImageViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: TitleDescriptionImageViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.description.text = items[position].description
        holder.title.text = items[position].title
        Glide.with(context).load(items[position].image).into(holder.image)

        holder.container.setOnClickListener {
            movieListener.onSelectItem(items[position])
        }


    }

    fun updateList(newList: List<Movie>){
        items = newList
        notifyDataSetChanged()
    }

    fun getList(): List<Movie> {
        return items
    }
}