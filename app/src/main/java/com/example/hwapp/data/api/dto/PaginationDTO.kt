package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class PaginationDTO(
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String,
    @SerializedName("numberOfPages")
    val numberOfPages: Int,
    @SerializedName("totalNumberOfItems")
    val totalNumberOfItems: Int
)