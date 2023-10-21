package com.example.renttoorange

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.renttoorange.view.Homepage
import com.example.renttoorange.view.LogIn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = getUsernameFromPreferences()

        // If the username is null (user not logged in), redirect to LogIn activity
        if (username == null) {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish() // This ensures that the MainActivity is not in the back stack after redirection
            return
        } else {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish() // This ensures that the MainActivity is not in the back stack after redirection
            return
        }
    }

    private fun getUsernameFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null)
    }
}