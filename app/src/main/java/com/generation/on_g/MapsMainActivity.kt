package com.generation.on_g

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsMainActivity : FragmentActivity()  {
    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maps_main_activity)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
        googleMap = it

        val location1 = LatLng(-23.54298,-46.75733)
            googleMap.addMarker(MarkerOptions().position(location1).title("Adoção de animais"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,10f))

        val location2 = LatLng(-23.54534955756445, -46.635964987184174)
            googleMap.addMarker(MarkerOptions().position(location2).title("Moradia e cidadania"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location2,10f))

        val location3= LatLng(-23.526607870889716, -46.65948000252669)
            googleMap.addMarker(MarkerOptions().position(location3).title("Voluntariado para professores"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location3,10f))

        val location4= LatLng(-23.28428907446927, -47.27698166020325)
            googleMap.addMarker(MarkerOptions().position(location4).title("Distribuição de alimentos"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location4,10f))

        val location5= LatLng(-23.65533609172279, -46.69381420252384)
            googleMap.addMarker(MarkerOptions().position(location5).title("Ajuda a moradores de rua"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location5,10f))

        })
    }

}