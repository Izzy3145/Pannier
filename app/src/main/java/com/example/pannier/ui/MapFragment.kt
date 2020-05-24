package com.example.pannier.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pannier.R
import com.example.pannier.utils.NavigationUtils
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.HillshadeLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.hillshadeHighlightColor
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.hillshadeShadowColor
import com.mapbox.mapboxsdk.style.sources.RasterDemSource


private const val LAYER_ID = "hillshade-layer"
private const val SOURCE_ID = "hillshade-source"
private const val SOURCE_URL = "mapbox://mapbox.terrain-rgb"
private const val HILLSHADE_HIGHLIGHT_COLOR = "#008924"

class MapFragment : Fragment() {

    private var mapView : MapView? = null
    lateinit var navigationUtils : NavigationUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(activity!!, getString(R.string.mapbox_access_token))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        setUpMap()



        navigationUtils = NavigationUtils(activity!!)
        val searchButton : Button = view.findViewById(R.id.search_button)
        searchButton.setOnClickListener {navigationUtils.searchRoute()}
        val startNavigationBtn : Button = view.findViewById(R.id.start_navigation)
        startNavigationBtn.setOnClickListener{startNavigationBtn}

        return view
    }


    fun setUpMap(){
        mapView?.getMapAsync{mapboxMap -> mapboxMap.setStyle(Style.MAPBOX_STREETS){
            val rasterDemSource: RasterDemSource = RasterDemSource(SOURCE_ID, SOURCE_URL);
            it.addSource(rasterDemSource)
            val hillshadeLayer : HillshadeLayer = HillshadeLayer(LAYER_ID, SOURCE_ID).withProperties(
                hillshadeHighlightColor(Color.parseColor(HILLSHADE_HIGHLIGHT_COLOR)),
                hillshadeShadowColor(Color.BLACK))
            // Add the hillshade layer to the map
            it.addLayerBelow(hillshadeLayer, "aerialway")
        }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            mapView?.onSaveInstanceState(outState)
        }
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView?.onDestroy()
    }


}