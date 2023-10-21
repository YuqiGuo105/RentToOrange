package com.example.renttoorange.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.renttoorange.R

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val tvUsername: TextView = findViewById(R.id.tvUsername)
        val btnLogout: Button = findViewById(R.id.btnLogout)

        // Retrieve the username from SharedPreferences
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")
        tvUsername.text = "Welcome, $username!"

        // Set Logout button click listener
        btnLogout.setOnClickListener {
            // Clear SharedPreferences
            sharedPreferences.edit().clear().apply()

            // Redirect to LogIn Activity
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }
    }
}