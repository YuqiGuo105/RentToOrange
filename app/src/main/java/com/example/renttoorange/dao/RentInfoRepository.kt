package com.example.renttoorange.dao

import android.util.Log
import com.example.renttoorange.model.RentInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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