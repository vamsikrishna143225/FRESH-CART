package com.example.freshcart

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //----------------------------------------------------------------------------------------
        val username = intent.getStringExtra("username")
        if (username != null) {
            Toast.makeText(this, "Welcome, $username!", Toast.LENGTH_SHORT).show()
        }

        // Set up toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        searchBar = findViewById(R.id.search_bar)

        var actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.navigation_drawer_open,R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_view_items -> {
                    // Open DisplayActivity to show all stored items
                    startActivity(Intent(this, CartActivity::class.java))
                }

                R.id.nav_track -> {
                    startActivity(Intent(this, TrackActivity::class.java))
                }

                R.id.nav_profile -> {
                    // Open ProfileActivity and pass the username
                    val username = intent.getStringExtra("username")
                    val profileIntent = Intent(this, ProfileActivity::class.java)
                    profileIntent.putExtra("username", username)
                    startActivity(profileIntent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        //-----------------------------------------------------------------------------------------
        val gridView = findViewById<GridView>(R.id.gridView)
        val arrayListImage = ArrayList<Int>()

        arrayListImage.add(R.drawable.apple)
        arrayListImage.add(R.drawable.banana)
        arrayListImage.add(R.drawable.grapes)
        arrayListImage.add(R.drawable.sberry)
        arrayListImage.add(R.drawable.oranges)
        arrayListImage.add(R.drawable.tomatoes)
        arrayListImage.add(R.drawable.brinjal)
        arrayListImage.add(R.drawable.potatoes)
        arrayListImage.add(R.drawable.carrot)
        arrayListImage.add(R.drawable.onions)

        val name = arrayOf("Apples", "Bananas", "Grapes", "Strawberries", "Oranges","Tomatoes", "Brinjal", "Potatoes", "Carrot", "Onions")

        val customAdapter = CustomGridAdapter(this@HomeActivity,
            arrayListImage, name)

        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { adapterView, parent, position, l ->
            Toast.makeText(this@HomeActivity,
                "Click on : " + name[position], Toast.LENGTH_LONG).show()
        }
    }

    // Helper function to show toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Handle back button press to close drawer if open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}