package com.example.freshcart

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class PlaceorderActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeorder)

        // Retrieve the item count from the intent
        val itemCount = intent.getIntExtra("ITEM_COUNT", 0)

        // Find the TextView and set the item count
        val itemCountTextView = findViewById<TextView>(R.id.item_count_text_view)
        itemCountTextView.text = "Number of items in your cart: $itemCount"

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val locationText = findViewById<TextView>(R.id.location_text)
        val confirmButton = findViewById<Button>(R.id.confirm_button)
        val finalButton = findViewById<Button>(R.id.btnf)

        // Check permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            fetchLocation(locationText)
        }

        finalButton.setOnClickListener {
            // Handle confirmation logic
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchLocation(locationText: TextView) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addresses != null && addresses.isNotEmpty()) {
                    val address = addresses[0].getAddressLine(0)
                    locationText.text = "Your Address: $address"
                } else {
                    locationText.text = "Unable to fetch address"
                }
            } else {
                locationText.text = "Location not found"
            }
        }
    }
}
