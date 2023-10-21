package com.example.renttoorange.view

import User
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository

class LogIn : AppCompatActivity() {
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        userRepository = UserRepository(this)

        val btnLogin: Button = findViewById(R.id.loginButton)
        val btnRegister: Button = findViewById(R.id.registerButton)
        val etEmail: EditText = findViewById(R.id.emailEditText)
        val etPassword: EditText = findViewById(R.id.passwordEditText)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            val user = userRepository.getUserByEmailAndPassword(email, password)

            if (user != null) {
                saveUserDetailsToPreferences(user)

                // Redirect to HomepageActivity
                val intent = Intent(this, Homepage::class.java)
                startActivity(intent)
                finish()
            } else {
                 Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserDetailsToPreferences(user: User) {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", user.username)
        editor.putString("userId", user.userId)
        editor.putString("userType", user.userType.name)  // Storing the enum as a string
        editor.apply()
    }
}