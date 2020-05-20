package com.example.pannier.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pannier.R
import com.example.pannier.models.Route

class RoutesListAdapter(val context: Context, routesList: ArrayList<Route>?, onRouteListener: OnRouteListener)
    :RecyclerView.Adapter<RoutesListAdapter.RouteViewHolder>() {

    val TAG = "RoutesListAdapter"
    val mOnRouteListener = onRouteListener
    var mRoutesList = routesList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.route_list_item, parent, false)
        val routeViewHolder = RouteViewHolder(view)
        return routeViewHolder
    }

    override fun getItemCount(): Int {
        return mRoutesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val routeItem = mRoutesList?.get(position)
        holder.routeTitle.text = routeItem?.title
    }

    inner class RouteViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var routeTitle : TextView = v.findViewById(R.id.route_title)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d(TAG, "onClick: $adapterPosition")
            mOnRouteListener.onRouteClick(mRoutesList?.get(adapterPosition))
        }
    }

    fun setRoutesListData(routesList: ArrayList<Route>){
        mRoutesList = routesList
        notifyDataSetChanged()
    }

    interface OnRouteListener{
        fun onRouteClick(route: Route?)
    }
}