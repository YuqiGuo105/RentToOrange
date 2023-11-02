package com.example.renttoorange.dao

import User
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.renttoorange.R
import java.io.ByteArrayOutputStream

class UserRepository(private val context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        val selection = "${DatabaseHelper.COLUMN_EMAIL} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = dbHelper.readableDatabase.query(
            DatabaseHelper.TABLE_USER,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val userId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID))
            val userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
            val userPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
            val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME))
            val userType = UserType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_TYPE)))

            cursor.close()
            return User(userId.toString(), userEmail, userPassword, username, userType)
        }
        cursor.close()
        return null
    }


    fun insertUser(user: User): Long {
        val db = dbHelper.writableDatabase
        val userImage = user.image ?: getDefaultImageByteArray(context)

        val contentValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_EMAIL, user.email)
            put(DatabaseHelper.COLUMN_PASSWORD, user.password)
            put(DatabaseHelper.COLUMN_USERNAME, user.username)
            put(DatabaseHelper.COLUMN_USER_TYPE, user.userType.toString())
            put(DatabaseHelper.COLUMN_IMAGE, userImage) // Insert image
        }

        return db.insert(DatabaseHelper.TABLE_USER, null, contentValues)
    }

    fun getUserByEmail(email: String): User? {
        val db = dbHelper.readableDatabase
        val selection = "${DatabaseHelper.COLUMN_EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        val cursor = db.query(
            DatabaseHelper.TABLE_USER,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        if (cursor?.moveToFirst() == true) {
            val user = User(
                userId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                userType = UserType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_TYPE))),
                image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE))
            )
            cursor.close()
            return user
        }

        cursor?.close()
        return null
    }

    fun getUserById(userId: String): User? {
        val db = dbHelper.readableDatabase
        val selection = "${DatabaseHelper.COLUMN_USER_ID} = ?"
        val selectionArgs = arrayOf(userId)

        val cursor = db.query(
            DatabaseHelper.TABLE_USER,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        if (cursor?.moveToFirst() == true) {
            val user = User(
                userId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                userType = UserType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_TYPE))),
                image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE))
            )
            cursor.close()
            return user
        }

        cursor?.close()
        return null
    }

    fun getDefaultImageByteArray(context: Context): ByteArray {
        val defaultImage = BitmapFactory.decodeResource(context.resources, R.drawable.otto) // Replace with your default image resource name
        val outputStream = ByteArrayOutputStream()
        defaultImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }


}

