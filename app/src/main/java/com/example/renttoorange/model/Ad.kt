// Ad.kt
package com.example.renttoorange.model

import com.google.firebase.database.PropertyName
import java.io.Serializable
import java.util.UUID

data class Ad(
    @PropertyName("id") val id: String = UUID.randomUUID().toString(),
    @PropertyName("title") val title: String = "",
    @PropertyName("address") val address: String = "",
    @PropertyName("description") val description: String = "",
    @PropertyName("imageUrl") val imageUrl: String = ""
) : Serializable
