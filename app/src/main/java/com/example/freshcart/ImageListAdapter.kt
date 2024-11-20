package com.example.freshcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageListAdapter(private val imageList: List<ImageData>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.name)
        val removeButton: Button = itemView.findViewById(R.id.remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newgriditem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageList[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.textView.text = item.name

        holder.removeButton.setOnClickListener {
            val dbHelper = NewDatabaseHelper(holder.itemView.context)

            // Remove from the database
            val isDeleted = dbHelper.deleteImage(item.id)
            if (isDeleted) {
                // Remove from the list and notify the adapter
                (imageList as MutableList).removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int = imageList.size
}
