package com.example.renttoorange.view

import User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository
import kotlin.random.Random

class Register : AppCompatActivity() {
    private val userRepository by lazy { UserRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val userTypeRadioGroup: RadioGroup = findViewById(R.id.userTypeRadioGroup)
        val etVerificationCode: EditText = findViewById(R.id.etVerificationCode)
        val btnSendVerification: Button = findViewById(R.id.btnSendVerification)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        var verificationCode: String? = "123456"

        btnSendVerification.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.endsWith("@syr.edu")) {
                // Generate verification code and send via email
//                verificationCode = generateVerificationCode()

                ///////////////////// Send Verification Code via Email /////////////////////
                Toast.makeText(this@Register, "Verification code sent to your email.", Toast.LENGTH_SHORT).show()
                ///////////////////////////////////////////////////////////////////////////

            } else {
                Toast.makeText(this, "Renters must use a @syr.edu email", Toast.LENGTH_SHORT).show()
            }
        }

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

            if (verificationCode == null || etVerificationCode.text.toString().trim() != verificationCode) {
                Toast.makeText(this, "Invalid verification code!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if user with the given email already exists
            if (userRepository.getUserByEmail(email) != null) {
                Toast.makeText(this, "Account with this email already exists!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = User(email = email, password = password, username = username, userType = userType)
            userRepository.insertUser(newUser)
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateVerificationCode(): String {
        return Random.nextInt(100000, 999999).toString()
    }
}