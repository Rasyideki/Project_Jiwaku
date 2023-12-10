package com.example.jiwaku.FiturMapLokasi

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah
import com.example.jiwaku.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // kembali
        binding.kembali.setOnClickListener {
            FunPindah.onBackPressed(this)
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnDrawState.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_maps_confirm, null)

            builder.setView(dialogView)
            val dialog = builder.create()

            dialogView.findViewById<Button>(R.id.btnReset).setOnClickListener {
                // Perform map search directly
                performMapSearch("-7.680784,110.4138637", "rumah sakit terdekat dari lokasi saya")
                dialog.dismiss()
            }
            dialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Enable zoom controls
        mMap.uiSettings.isZoomControlsEnabled = true

        // Add a marker in hospital and move the camera
        val hospital = LatLng(-7.6829531,110.4140471)
        mMap.addMarker(MarkerOptions().position(hospital).title("Lokasi Rumah Sakit Terdekat"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hospital, 12.0f))

        // Set up other map settings
        val uiSettings: UiSettings = mMap.uiSettings
        uiSettings.isZoomGesturesEnabled = true
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isRotateGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = true

    }

    private fun performMapSearch(coordinates: String, query: String) {
        try {
            // Create the Google Maps URL with a search query for the nearest hospital
            val mapsUrl = "https://www.google.com/maps/search/$query/@$coordinates,14.94z"

            // Open the URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl))
            startActivity(intent)
        } catch (e: Exception) {
            // Handle exception
        }
    }
}