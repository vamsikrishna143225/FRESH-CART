package com.example.freshcart

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NewDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "images.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "image_data"
        const val COLUMN_ID = "id"
        const val COLUMN_IMAGE = "imageResId"
        const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_IMAGE INTEGER, " +
                "$COLUMN_NAME TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertImage(imageResId: Int, name: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_IMAGE, imageResId)
        contentValues.put(COLUMN_NAME, name)

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllImages(): ArrayList<ImageData> {
        val db = this.readableDatabase
        val imageList = ArrayList<ImageData>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                imageList.add(ImageData(id, imageResId, name))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return imageList
    }

    fun deleteImage(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return result > 0 // Returns true if an item was deleted
    }
}
