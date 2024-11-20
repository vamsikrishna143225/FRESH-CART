package com.example.freshcart

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class CustomGridAdapter(
    private val context: Context,
    private val arrayListImage: ArrayList<Int>,
    private val name: Array<String>
) : BaseAdapter() {

    private val databaseHelper = NewDatabaseHelper(context)

    override fun getCount(): Int = arrayListImage.size
    override fun getItem(position: Int): Any = arrayListImage[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        val holder: ViewHolder

        if (myView == null) {
            val inflater = (context as Activity).layoutInflater
            myView = inflater.inflate(R.layout.griditem, parent, false)
            holder = ViewHolder()

            holder.mImageView = myView.findViewById(R.id.image)
            holder.mTextView = myView.findViewById(R.id.name)
            holder.saveButton = myView.findViewById(R.id.add)

            myView.tag = holder
        } else {
            holder = myView.tag as ViewHolder
        }

        holder.mImageView?.setImageResource(arrayListImage[position])
        holder.mTextView?.text = name[position]

        // Save to SQLite database on button click
        holder.saveButton?.setOnClickListener {
            databaseHelper.insertImage(arrayListImage[position], name[position])

            // Show a toast message to confirm the item was added to the cart
            Toast.makeText(context, "${name[position]} added to cart", Toast.LENGTH_SHORT).show()
        }

        return myView!!
    }

    class ViewHolder {
        var mImageView: ImageView? = null
        var mTextView: TextView? = null
        var saveButton: Button? = null
    }
}
