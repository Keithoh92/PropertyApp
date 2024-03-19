package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class CityDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("idCountry")
    val idCountry: Int,
    @SerializedName("country")
    val country: String
)