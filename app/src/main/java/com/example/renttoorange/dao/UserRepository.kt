package com.example.renttoorange.dao

import User
import UserType
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository(private val auth: FirebaseAuth) {

    fun fetchUserInfo(): User? {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return firebaseUser?.let {
            // Email and Photo URL from Firebase
            val email = it.email ?: ""
            val photoUrl = it.photoUrl.toString()

            // Placeholder for username and userType
            // Replace these with actual logic to retrieve or infer these values
            val username = "SomeUsername" // Placeholder
            val userType = UserType.RENTER // Placeholder

            // Create and return the User object
            User(
                userId = it.uid,
                email = email,
                username = username,
                userType = userType,
                image = photoUrl
            )
        }
    }

    fun registerUser(email: String, password: String, username: String, userType: UserType, callback: (User?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Create and return the custom User object
                    val firebaseUser = auth.currentUser
                    val user = firebaseUser?.let {
                        User(
                            userId = it.uid,
                            email = email,
                            username = username,
                            userType = userType,
                            image = it.photoUrl.toString() // or any default image URL
                        )
                    }
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }

}

