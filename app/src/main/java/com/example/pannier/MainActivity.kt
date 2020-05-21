package com.example.pannier

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pannier.models.Route
import com.example.pannier.ui.ListFragment
import com.example.pannier.ui.MapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ListFragment.OnRouteSelectedListener {

    lateinit var app : Context

    var routeViewModel: RouteViewModel?= null

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationView()
        routeViewModel = ViewModelProvider(this)[RouteViewModel::class.java]
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
        routeViewModel?.setSelectedRoute(route)
    }


}

/*
class MainActivity : BaseActivity(), OnRouteSelectedListener {
    @Inject
    var providerFactory: ViewModelProviderFactory? = null
    var bottomNavigationView: BottomNavigationView? = null
    var routeViewModel: RouteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavView()
        routeViewModel =
            ViewModelProviders.of(this, providerFactory)[RouteViewModel::class.java]
        val pm = packageManager
        if (!pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) { // This device does not have a compass, turn off the compass feature
        }
    }

    fun setUpBottomNavView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, ListFragment())
                    .commit()
                R.id.mapFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, MapFragment())
                    .commit()
                R.id.weatherFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, WeatherFragment())
                    .commit()
                R.id.profileFragment -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, ProfileFragment())
                    .commit()
            }
            true
        })
        bottomNavigationView.setSelectedItemId(R.id.listFragment)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is ListFragment) {
            fragment.setOnRouteSelectedListener(this)
        }
    }

    fun onRouteSelected(route: Route?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, MapFragment())
            .commit()
        routeViewModel!!.setSelectedRoute(route!!)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}*/


