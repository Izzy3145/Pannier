package com.example.pannier

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pannier.models.Route

class RouteViewModel : ViewModel() {

    var selectedRoute = MutableLiveData<Route>()

    fun fetchSelectedRoute() : MutableLiveData<Route>{
        return selectedRoute
    }

    fun setSelectedRoute(route : Route?){
        selectedRoute.value = route

    }
}
