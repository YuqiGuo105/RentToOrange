package com.example.renttoorange.dao

import User
import android.content.Context
import android.database.Cursor

class UserRepository(private val context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        val db = dbHelper.readableDatabase
        val selectQuery = "SELECT * FROM ${DatabaseHelper.TABLE_USER} WHERE ${DatabaseHelper.COLUMN_EMAIL} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?"
        val cursor: Cursor = db.rawQuery(selectQuery, arrayOf(email, password))

        var user: User? = null

        val userIdIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID).takeIf { it >= 0 }
        val emailIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL).takeIf { it >= 0 }
        val passwordIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD).takeIf { it >= 0 }
        val usernameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME).takeIf { it >= 0 }
        val userTypeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_TYPE).takeIf { it >= 0 }

        if (cursor.moveToFirst() &&
            userIdIndex != null &&
            emailIndex != null &&
            passwordIndex != null &&
            usernameIndex != null &&
            userTypeIndex != null) {

            user = User(
                userId = cursor.getString(userIdIndex),
                email = cursor.getString(emailIndex),
                password = cursor.getString(passwordIndex),
                username = cursor.getString(usernameIndex),
                userType = UserType.valueOf(cursor.getString(userTypeIndex))
            )
        }

        cursor.close()
        return user
    }

}

