package com.example.renttoorange.view

import User
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository

class Homepage : AppCompatActivity() {
    private lateinit var profileImageView: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var logoutButton: Button
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Initialize views and repository
        profileImageView = findViewById(R.id.ivUserProfile)
        usernameTextView = findViewById(R.id.tvUsername)
        logoutButton = findViewById(R.id.btnLogout)
        userRepository = UserRepository(this)

        // Load user information
        loadUserInfo()

        // Setup logout button
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun loadUserInfo() {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", null)
        userEmail?.let {
            val user = userRepository.getUserByEmail(it)
            user?.let { user ->
                updateUI(user)
            }
        }
    }

    private fun updateUI(user: User) {
        usernameTextView.text = user.username

        user.image?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            profileImageView.setImageBitmap(bitmap)
        } ?: run {
            // Handle the case where the image is null, e.g., set a default image
            profileImageView.setImageResource(R.drawable.otto)
        }
    }

    private fun logout() {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        // Clear SharedPreferences
        sharedPreferences.edit().clear().apply()

        // Navigate back to the login screen
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()  // Close the current activity
    }
}