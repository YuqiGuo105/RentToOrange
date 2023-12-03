package com.example.renttoorange.view.MainFragments

import User
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.renttoorange.R
import com.example.renttoorange.dao.UserRepository
import com.example.renttoorange.view.LogIn
import com.example.renttoorange.view.Rental.PostRentInfo

class UserAccountFragment : Fragment() {
    private lateinit var usernameTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var logoutButton: Button

    private lateinit var userRepository: UserRepository

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userRepository = UserRepository(context)  // Initialize userRepository with the context.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTextView = view.findViewById(R.id.usernameTextView)
        profileImageView = view.findViewById(R.id.profileImageView)

        loadUserInfo()

        logoutButton = view.findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }

        val btnNavigate = view.findViewById<Button>(R.id.postRentInfo)
        btnNavigate.setOnClickListener {
            val intent = Intent(activity, PostRentInfo::class.java)
            startActivity(intent)
        }
    }

    private fun loadUserInfo() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences?.getString("userEmail", null)
        userEmail?.let {
            val user = userRepository.getUserByEmail(it)
            user?.let { user ->
                updateUI(user)
            }
        }
    }

    private fun updateUI(user: User) {
        usernameTextView.text = user.username

        // Convert ByteArray to Bitmap if the image data is not null and not empty
        user.image?.let { imageByteArray ->
            if (imageByteArray.isNotEmpty()) {
                val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                profileImageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun logout() {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        // Clear SharedPreferences
        sharedPreferences.edit().clear().apply()

        // Navigate back to the login screen
        val intent = Intent(activity, LogIn::class.java)
        startActivity(intent)
        requireActivity().finish()  // Close the current activity
    }
}