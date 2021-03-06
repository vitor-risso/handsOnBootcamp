package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class Flow : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return when (arguments?.get("number")!!) {
            1 -> inflater.inflate(R.layout.fragment_one, container, false)
            2 -> inflater.inflate(R.layout.fragment_two, container, false)
            else -> throw NullPointerException()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.nextFlow)
        )
    }

}