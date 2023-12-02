package com.example.renttoorange.dao

import com.example.renttoorange.model.RentInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RentInfoRepository {
    private var database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("rentInfo")

    fun saveRentInfo(rentInfo: RentInfo, callback: (Boolean) -> Unit) {
        rentInfo.Id?.let { id ->
            database.child(id).setValue(rentInfo).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        } ?: callback(false)
    }
}