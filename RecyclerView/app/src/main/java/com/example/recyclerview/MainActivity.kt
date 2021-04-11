package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.model.Adapter
import com.example.recyclerview.model.Data
import com.example.recyclerview.model.Name

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var data: List<Name>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populate()
    }

    private fun populate() {
        data = Data().getData()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = Adapter(this, data)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}