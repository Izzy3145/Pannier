package com.example.pannier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pannier.R

class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // return inflater.inflate(R.layout.fragment_map, container, false)
        return TextView(activity).apply {
            setText(R.string.hello_map_fragment)
        }
    }

}