package com.example.renttoorange.dao

import com.example.renttoorange.model.RentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class RentInfoRepository {
    private var database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("rentInfo")

    fun saveRentInfo(rentInfo: RentInfo, callback: (Boolean) -> Unit) {
        rentInfo.id?.let { id ->
            database.child(id).setValue(rentInfo).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        } ?: callback(false)
    }

    fun getRentInfoByType(type: String, callback: (List<RentInfo>) -> Unit) {
        val query = FirebaseDatabase.getInstance().reference.child("rentInfo")
            .orderByChild("category")
            .equalTo(type)

        query.get().addOnSuccessListener { dataSnapshot ->
            val rentInfoList = mutableListOf<RentInfo>()
            for (item in dataSnapshot.children) {
                item.getValue<RentInfo>()?.let { rentInfo ->
                    rentInfoList.add(rentInfo)
                }
            }
            callback(rentInfoList)
        }.addOnFailureListener {
            // Handle the failure case, e.g., by returning an empty list or logging the error
            callback(emptyList())
        }
    }


    fun retrieveAllRentInfo(callback: (List<RentInfo>?) -> Unit) {
        database.get().addOnSuccessListener {
            val rentInfoList = mutableListOf<RentInfo>()
            for (item in it.children) {
                item.getValue<RentInfo>()?.let { rentInfo ->
                    rentInfoList.add(rentInfo)
                }
            }
            callback(rentInfoList)
        }.addOnFailureListener {
            callback(null)
        }
    }


}