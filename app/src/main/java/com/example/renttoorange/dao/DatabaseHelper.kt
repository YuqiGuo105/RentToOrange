package com.example.renttoorange.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.renttoorange.R
import java.io.ByteArrayOutputStream
import java.util.UUID

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_USER_TYPE = "user_type"
        const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " TEXT PRIMARY KEY,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT,"
                + COLUMN_IMAGE + " BLOB" + ")")
        db.execSQL(CREATE_USER_TABLE)

        insertExampleUser(db) // Inserting an example user
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    private fun insertExampleUser(db: SQLiteDatabase) {
        val defaultImage = getDefaultImageByteArray()

        val exampleUserValues = ContentValues().apply {
            put(COLUMN_USER_ID, UUID.randomUUID().toString())
            put(COLUMN_EMAIL, "otto@syr.edu")
            put(COLUMN_PASSWORD, "1234")
            put(COLUMN_USERNAME, "Otto")
            put(COLUMN_USER_TYPE, UserType.RENTER.name)
            put(COLUMN_IMAGE, defaultImage) // Adding the default image
        }
        db.insert(TABLE_USER, null, exampleUserValues)
    }

    private fun getDefaultImageByteArray(): ByteArray {
        val defaultImage = BitmapFactory.decodeResource(context.resources, R.drawable.otto) // Replace with your default image resource name
        val outputStream = ByteArrayOutputStream()
        defaultImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

}
