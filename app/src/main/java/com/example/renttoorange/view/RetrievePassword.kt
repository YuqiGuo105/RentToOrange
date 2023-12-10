package com.example.renttoorange.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.renttoorange.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class RetrievePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_password)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val btnSendInstructions: Button = findViewById(R.id.btnSendInstructions)
        val tvBackToLogin: TextView = findViewById(R.id.tvBackToLogin)

        btnSendInstructions.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if (email.isNotEmpty()) {

                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Email sent!", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please input valid email address!", Toast.LENGTH_SHORT).show()
            }
        }

        tvBackToLogin.setOnClickListener {
            // Navigate back to the login activity
            finish()
        }
    }
}