package com.example.renttoorange.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.renttoorange.R
import com.example.renttoorange.view.MainFragments.CategoryFragment
import com.example.renttoorange.view.MainFragments.HomePageFragment
import com.example.renttoorange.view.MainFragments.UserAccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homepage : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomePageFragment())
                    true
                }
                R.id.nav_category -> {
                     replaceFragment(CategoryFragment())
                    true
                }
                R.id.nav_user_account -> {
                     replaceFragment(UserAccountFragment())
                    true
                }
                else -> false
            }
        }

        // Select 'home' by default
        bottomNavigationView.selectedItemId = R.id.nav_home
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
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