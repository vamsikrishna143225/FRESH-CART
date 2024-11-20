package com.example.freshcart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter
    private val databaseHelper by lazy { NewDatabaseHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val images = databaseHelper.getAllImages()
        adapter = ImageListAdapter(images)
        recyclerView.adapter = adapter

        val buyNowButton = findViewById<Button>(R.id.buy_now_button)

        // Get the count of items in the cart
        val itemCount = images.size

        buyNowButton.setOnClickListener {
            val intent = Intent(this, PlaceorderActivity::class.java)

            // Add the item count as an extra to the intent
            intent.putExtra("ITEM_COUNT", itemCount)

            startActivity(intent)
        }
    }
}
