package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("city")
    val city: CityDTO,
    @SerializedName("region")
    val region: Any?
)
