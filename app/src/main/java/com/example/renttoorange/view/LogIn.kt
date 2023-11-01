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
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LogIn : AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private val STORAGE_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)

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

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@LogIn, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@LogIn, arrayOf(permission), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // Permission denied
                    showPermissionDialog()
                }
            }
        }
    }

    private fun showPermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission needed")
            .setMessage("This permission is needed to access your files")
            .setPositiveButton("Okay") { _, _ ->
                ActivityCompat.requestPermissions(this@LogIn, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create().show()
    }
}