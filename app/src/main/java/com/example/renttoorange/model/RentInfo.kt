package com.example.renttoorange.model

import java.util.UUID
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RentInfo(
    @SerializedName("Id") val Id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("landlordInfo") val landlordInfo: String,
    @SerializedName("rent") val rent: String,
    @SerializedName("category") val category: HouseType,
    @SerializedName("postedUserId") val postedUserId: String
) : Serializable

enum class HouseType {
    APARTMENT, HOUSE, STUDIO
}


