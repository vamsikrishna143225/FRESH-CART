package com.example.freshcart

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Retrieve the username from the Intent
        val username = intent.getStringExtra("username")

        // Find the TextView and set the username
        val usernameTextView = findViewById<TextView>(R.id.tvUsername)
        usernameTextView.text = "$username"
    }
}

