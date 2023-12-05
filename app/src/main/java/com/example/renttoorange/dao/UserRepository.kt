package com.example.renttoorange.dao

import User
import UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository(private val auth: FirebaseAuth) {
    private val firebaseDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    fun fetchUserInfo(callback: (User?) -> Unit) {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val userId = firebaseUser.uid
            // Assuming the user data is stored under a 'users' node with the userId as key
            val userRef = firebaseDatabase.reference.child("users").child(userId)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    callback(user)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g. insufficient permissions
                    callback(null)
                }
            })
        } else {
            // No user is logged in
            callback(null)
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
                            image = "https://firebasestorage.googleapis.com/v0/b/renttoorange-5f4fc.appspot.com/o/user%2Fotto.png?alt=media&token=bf33b882-d98f-4478-862e-62c1e43eee3c" // or any default image URL
                        )
                    }
                    user?.let { saveUserToDatabase(it) }
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }

    private fun saveUserToDatabase(user: User) {
        firebaseDatabase.reference.child("users").child(user.userId).setValue(user)
    }

}

