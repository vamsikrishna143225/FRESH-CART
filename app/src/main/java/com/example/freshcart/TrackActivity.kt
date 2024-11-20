package com.example.freshcart

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.Locale

class TrackActivity : AppCompatActivity() {

    private lateinit var etCityName: EditText
    private lateinit var btnShowOnMap: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        etCityName = findViewById(R.id.etCityName)
        btnShowOnMap = findViewById(R.id.btnShowOnMap)

        btnShowOnMap.setOnClickListener {
            val cityName = etCityName.text.toString().trim()
            if (cityName.isEmpty()) {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            } else {
                openCityOnMap(cityName)
            }
        }
    }

    private fun openCityOnMap(cityName: String) {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addressList: List<Address>? = geocoder.getFromLocationName(cityName, 1) // Nullable

            if (!addressList.isNullOrEmpty()) {
                val address = addressList[0]
                val latitude = address.latitude
                val longitude = address.longitude

                val uri = Uri.parse("geo:$latitude,$longitude?q=$cityName")
                val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "Google Maps app not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "City not found. Please enter a valid city name.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            Toast.makeText(this, "Error fetching location: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}
