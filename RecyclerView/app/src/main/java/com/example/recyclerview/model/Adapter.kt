package com.example.recyclerview.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class Adapter(
    private val context: Context,
    private val data: List<Name>
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.title)
    }

    init {
        Log.d("VITOR", "TEST")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.text.text = data[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d("VITOR", "TESTE")
        val view = inflater.inflate(R.layout.activity_items, parent, false)
        return ItemViewHolder(view)
    }


}