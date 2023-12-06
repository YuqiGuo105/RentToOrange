package com.example.renttoorange.model

import com.google.firebase.database.PropertyName
import java.util.UUID
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ad(
    @PropertyName("id") val id: String = UUID.randomUUID().toString(),
    @PropertyName("title") val title: String = "",
    @PropertyName("description") val description: String = "",
    @PropertyName("address") val address: String = "",
    @PropertyName("imageUrl") val imageUrl: String = "",
    @PropertyName("landlordInfo") val landlordInfo: String = "",
    @PropertyName("rent") val rent: String = "",
    @PropertyName("sponsorInfo") val sponsorInfo: String = "",
    @PropertyName("isSponsored") val isSponsored: Boolean = true,
    @PropertyName("category") val category: HouseType = HouseType.APARTMENT,
    @PropertyName("postedUserId") val postedUserId: String = ""

) : Serializable
//
//enum class HouseType {
//    APARTMENT, HOUSE, STUDIO
//}
