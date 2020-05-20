package com.example.pannier.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pannier.R
import com.example.pannier.adapter.RoutesListAdapter
import com.example.pannier.models.Route
import java.util.*

class ListFragment : Fragment(), RoutesListAdapter.OnRouteListener{

    lateinit var routesListAdapter: RoutesListAdapter
    lateinit var recyclerView: RecyclerView
    var routes = ArrayList<Route>()
    var mainActivityCallback: OnRouteSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        routesListAdapter = RoutesListAdapter(activity!!, routes, this)
        recyclerView = view.findViewById(R.id.routes_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = routesListAdapter

        routes.add(Route("Oxford to Cambridge", "oxfordcambridge", null))
        routes.add(Route("Coast and Castles", "coastandcastles", null))
        routes.add(Route("Penine Way", "penineway", null))
        routesListAdapter.setRoutesListData(routes)
    }

    override fun onRouteClick(route: Route?) {
        mainActivityCallback?.onRouteSelected(route)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRouteSelectedListener) {
            mainActivityCallback = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRouteSelectedListener")
        }
    }

    interface OnRouteSelectedListener{
        fun onRouteSelected(route: Route?)
    }

}
