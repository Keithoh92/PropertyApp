package com.example.hwapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class PropertyEntity(
    @PrimaryKey
    val propertyId: Long,
    val name: String,
    val ratingOverall: Double,
    val numberOfRatings: String,
    val type: Int,
    val distance: Double,
    val distanceUnit: String,

    val lowestPricePerNightValue: String? = null,
    val lowestPricePerNightCurrency: String? = null,
    val lowestDormPricePerNightValue: String? = null,
    val lowestDormPricePerNightCurrency: String? = null,
    val lowestPrivatePricePerNightValue: String? = null,
    val lowestPrivatePricePerNightCurrency: String? = null,

    val lowestAveragePrivatePricePerNightValue: String? = null,
    val lowestAveragePrivatePricePerNightCurrency: String? = null,
    val lowestAveragePrivatePricePerNightDiscount: String? = null,
    val lowestAveragePrivatePricePerNightOriginal: String? = null,

    val lowestAverageDormPricePerNightValue: String? = null,
    val lowestAverageDormPricePerNightCurrency: String? = null,
    val lowestAverageDormPricePerNightDiscount: String? = null,
    val lowestAverageDormPricePerNightOriginal: String? = null,

    val isFreeCancellation: Boolean,
    val isFeatured: Boolean,
    val isNew: Boolean,
    val isPromoted: Boolean,
    val isRecommended: Boolean,
    val city: String,
    val overview: String
)