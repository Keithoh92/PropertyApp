package com.example.hwapp.domain.model

import com.example.hwapp.domain.enums.PropertyHighlights
import com.example.hwapp.domain.enums.PropertyType

data class Property(
    val propertyId: Long,
    val name: String,
    val ratingOverall: Double,
    val numberOfRatings: String,
    val type: PropertyType,
    val distance: Double,
    val distanceUnit: String,

    val lowestPricePerNightValue: String? = null,
    val lowestPricePerNightCurrency: String? = null,

    val lowestPrivatePricePerNightValue: String? = null,
    val lowestPrivatePricePerNightCurrency: String? = null,

    val lowestDormPricePerNightValue: String? = null,
    val lowestDormPricePerNightCurrency: String? = null,

    val lowestAveragePrivatePricePerNightValue: String? = null,
    val lowestAveragePrivatePricePerNightCurrency: String? = null,
    val lowestAveragePrivatePricePerNightDiscount: String? = null,
    val lowestAveragePrivatePricePerNightOriginal: String? = null,

    val lowestAverageDormPricePerNightValue: String? = null,
    val lowestAverageDormPricePerNightCurrency: String? = null,
    val lowestAverageDormPricePerNightDiscount: String? = null,
    val lowestAverageDormPricePerNightOriginal: String? = null,

    val labelList: List<PropertyHighlights>,
    val images: List<String> = emptyList(),
    val city: String,
    val overview: String
) {
    fun isDiscountedPrivate(): Boolean {
        return lowestAveragePrivatePricePerNightDiscount != null
                && lowestAveragePrivatePricePerNightValue != null
    }

    fun calculatePrivateDiscount(): Double {
        return if (lowestAveragePrivatePricePerNightOriginal == null) {
            val discount = (lowestAveragePrivatePricePerNightValue?.toDouble()
                ?.div(lowestAveragePrivatePricePerNightDiscount?.toDouble()!!))?.times(100) ?: 0.0
            formatDiscount(discount.toString()).toDouble()
        } else {
            formatDiscount(lowestAveragePrivatePricePerNightOriginal).toDouble()
        }
    }

    fun isDiscountedDorm(): Boolean {
        return lowestAverageDormPricePerNightDiscount != null
                && lowestAverageDormPricePerNightValue != null
    }

    fun calculateDormDiscount(): Double {
        return if (lowestAverageDormPricePerNightOriginal == null) {
            val discount = (lowestAverageDormPricePerNightValue?.toDouble()
                ?.div(lowestAverageDormPricePerNightDiscount?.toDouble()!!))?.times(100) ?: 0.0
            formatDiscount(discount.toString()).toDouble()
        } else {
            formatDiscount(lowestAverageDormPricePerNightOriginal).toDouble()
        }
    }

    fun formatDiscount(discount: String): String {
        val discountValue = discount.toDoubleOrNull()
        return if (discountValue != null) {
            if (discountValue % 1 == 0.0) {
                discountValue.toInt().toString()
            } else {
                String.format("%.1f", discountValue)
            }
        } else {
            ""
        }
    }
}