package com.example.pannier.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.pannier.R
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.DirectionsCriteria.PROFILE_CYCLING
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.geojson.Point
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class NavigationUtils(var activity: Activity) {

    init {
        val navigation = MapboxNavigation(activity!!, activity.getString(R.string.mapbox_access_token))

    }

    fun searchRoute(originLat: Double = 38.8977, originLon: Double = -77.03613,
                    destinationLat: Double = 38.8977, destinationLon:  Double = -77.0365) {
        // val origin = Point.fromLngLat(-77.03613, 38.8977)
        // val destination = Point.fromLngLat(-77.0365, 38.8977)

        val origin = Point.fromLngLat(originLon, originLat)
        val destination = Point.fromLngLat(destinationLon, destinationLat)

        NavigationRoute.builder(activity!!)
            .accessToken(activity.getString(R.string.mapbox_access_token))
            .origin(origin)
            .destination(destination)
            .profile(DirectionsCriteria.PROFILE_CYCLING)
            .build()
            .getRoute(object : Callback<DirectionsResponse?> {
                override fun onResponse(call: Call<DirectionsResponse?>, response: Response<DirectionsResponse?>) {
                    response.body()?.let {
                        val simulateRoute = true

                        Log.i("NavigationUtils", "onResponse: " + response.body())


                        // Create a NavigationLauncherOptions object to package everything together
                        val options: NavigationLauncherOptions = NavigationLauncherOptions.builder()
                            .directionsRoute(it.routes()[0])
                            .shouldSimulateRoute(simulateRoute)
                            .build()


                        NavigationLauncher.startNavigation(activity, options)

                    }
                }

                override fun onFailure(call: Call<DirectionsResponse?>, t: Throwable) {
                    Timber.i( String.format("onFailure: ", t.message))
                }
            })
    }
    }

