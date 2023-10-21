package com.example.renttoorange.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.UUID

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_USER_TYPE = "user_type"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " TEXT PRIMARY KEY,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT" + ")")
        db.execSQL(CREATE_USER_TABLE)

        insertExampleUser(db) // Inserting an example user
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    private fun insertExampleUser(db: SQLiteDatabase) {
        val exampleUserValues = ContentValues().apply {
            put(COLUMN_USER_ID, UUID.randomUUID().toString())
            put(COLUMN_EMAIL, "example@syr.edu")
            put(COLUMN_PASSWORD, "1234")
            put(COLUMN_USERNAME, "ExampleUser")
            put(COLUMN_USER_TYPE, UserType.RENTER.name)
        }
        db.insert(TABLE_USER, null, exampleUserValues)
    }
}
