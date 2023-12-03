package com.example.renttoorange.dao

import com.google.firebase.storage.FirebaseStorage
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask

class ImageUploader {

    // Specify the bucket URL here
    private val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://renttoorange-5f4fc.appspot.com/rentInfo")

    fun uploadImage(imageUri: Uri, callback: (String?) -> Unit) {
        // Here we use the filename derived from the imageUri, but you might want to use a unique name
        val fileName = imageUri.lastPathSegment ?: "image_${System.currentTimeMillis()}"
        val fileReference = storageReference.child("images/$fileName")

        fileReference.putFile(imageUri).addOnSuccessListener { taskSnapshot: UploadTask.TaskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri: Uri ->
                callback(uri.toString())
            }
        }.addOnFailureListener {
            callback(null)
        }
    }
}
