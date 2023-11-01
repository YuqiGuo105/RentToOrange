package com.example.renttoorange

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import com.example.renttoorange.view.Homepage
import com.example.renttoorange.view.LogIn
import android.os.Handler

class MainActivity : AppCompatActivity() {
    private lateinit var ottoImageView: ImageView
    private lateinit var houseImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ImageView references
        ottoImageView = findViewById(R.id.imageView4)
        houseImageView = findViewById(R.id.imageView)

        animateOtto()

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginAndRedirect()
        }, 3000) // Adjust delay to match the total animation time
    }

    private fun animateOtto() {
        val x = PropertyValuesHolder.ofFloat("translationX", 100f) // Adjust for desired distance
        val y = PropertyValuesHolder.ofFloat("translationY", 100f) // Adjust for desired distance

        val animator = ObjectAnimator.ofPropertyValuesHolder(ottoImageView, x, y)
        animator.duration = 2000 // duration in milliseconds (2 seconds)
        animator.start()
    }

    private fun checkLoginAndRedirect() {
        val username = getUsernameFromPreferences()

        if (username == null) {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getUsernameFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null)
    }
}