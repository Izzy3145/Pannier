package com.example.pannier

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pannier.models.Route
import com.example.pannier.ui.ListFragment
import com.example.pannier.ui.MapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ListFragment.OnRouteSelectedListener {

    @Inject
    lateinit var app : Context

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationView()
    }

    fun setUpBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.listFragment -> setMainFragment(ListFragment())
                R.id.mapFragment -> setMainFragment(MapFragment())
                else -> false
            }
        })
        bottomNavigationView.selectedItemId = R.id.listFragment
    }

    fun setMainFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit()
        return true
    }

    override fun onRouteSelected(route: Route?) {
        supportFragmentManager.beginTransaction().replace(R.id.main_content, MapFragment()).commit()

    }


}

