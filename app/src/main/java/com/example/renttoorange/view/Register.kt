package com.example.renttoorange.view

import User
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        userRepository = UserRepository(auth)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val userTypeRadioGroup: RadioGroup = findViewById(R.id.userTypeRadioGroup)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = findViewById<EditText>(R.id.etConfirmPasswordRegister).text.toString()
            val username = etUsername.text.toString().trim()
            val userType = when (userTypeRadioGroup.checkedRadioButtonId) {
                R.id.radioRenter -> UserType.RENTER
                R.id.radioLandlord -> UserType.LANDLORD
                else -> null
            }

            if (email.isEmpty() || password.isEmpty() || username.isEmpty() || userType == null) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userRepository.registerUser(email, password, username, userType) { user ->
                if (user != null) {
                    // Registration success, send email verification

                    auth.currentUser!!.sendEmailVerification().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("EmailVerification", "Email sent.")
                            Toast.makeText(this, "Registration successful! Check email to activate account!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, LogIn::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Handle failure in sending email
                            Toast.makeText(this, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Registration failure, update UI
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        }
    }

}