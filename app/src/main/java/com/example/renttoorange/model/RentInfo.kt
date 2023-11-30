package com.example.renttoorange.model

import java.util.UUID


data class RentInfo(
    val Id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val address: String,
    val imageUrl: String,
    val landlordInfo: String,
    val rent: Double,
    val category: HouseType
)

enum class HouseType {
    APARTMENT, HOUSE, STUDIO
}


