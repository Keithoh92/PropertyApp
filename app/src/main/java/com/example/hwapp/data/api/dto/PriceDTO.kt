package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class PriceDTO(
    @SerializedName("value")
    val value: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("promotions")
    val promotions: PromotionsDTO? = null,
    @SerializedName("original")
    val original: String? = null
)

data class PromotionsDTO(
    @SerializedName("totalDiscount")
    val totalDiscount: String
)