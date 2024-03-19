package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class PropertyApiResponse(
    @SerializedName("properties")
    val properties: List<PropertyDTO>,
    @SerializedName("location")
    val location: LocationDTO,
    @SerializedName("locationEn")
    val locationEn: LocationDTO,
    @SerializedName("filterData")
    val filterData: FilterDataDTO,
    @SerializedName("sortOrder")
    val sortOrder: String?,
    @SerializedName("pagination")
    val pagination: PaginationDTO
)