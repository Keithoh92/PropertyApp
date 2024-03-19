package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class FilterDataDTO(
    @SerializedName("highestPricePerNight")
    val highestPricePerNight: PriceDTO,
    @SerializedName("lowestPricePerNight")
    val lowestPricePerNight: PriceDTO
)