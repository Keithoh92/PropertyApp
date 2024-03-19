package com.example.hwapp.domain.mapper

import com.example.hwapp.domain.model.Property
import com.example.hwapp.domain.enums.PropertyType
import com.example.hwapp.data.database.entity.PropertyEntity
import com.example.hwapp.data.api.dto.PropertyDTO
import com.example.hwapp.domain.enums.PropertyHighlights

fun PropertyDTO.toPropertyEntity(locationResponse: String?): PropertyEntity {
    return PropertyEntity(
        propertyId = id,
        name = name,
        ratingOverall = overallRating.overall,
        numberOfRatings = overallRating.numberOfRatings,
        type = PropertyType.valueOf(type).ordinal,
        distance = distance.value,
        distanceUnit = distance.units,

        lowestPricePerNightValue = lowestPricePerNight?.value,
        lowestPrivatePricePerNightValue = lowestPrivatePricePerNight?.value,
        lowestDormPricePerNightValue = lowestDormPricePerNight?.value,
        lowestPricePerNightCurrency = lowestPricePerNight?.currency,
        lowestPrivatePricePerNightCurrency = lowestPrivatePricePerNight?.currency,
        lowestDormPricePerNightCurrency = lowestDormPricePerNight?.currency,

        lowestAveragePrivatePricePerNightValue = lowestAveragePrivatePricePerNight?.value,
        lowestAveragePrivatePricePerNightCurrency = lowestAveragePrivatePricePerNight?.currency,
        lowestAveragePrivatePricePerNightDiscount = lowestAveragePrivatePricePerNight?.promotions?.totalDiscount,
        lowestAveragePrivatePricePerNightOriginal = lowestAveragePrivatePricePerNight?.original,

        lowestAverageDormPricePerNightValue = lowestAverageDormPricePerNight?.value,
        lowestAverageDormPricePerNightCurrency = lowestAverageDormPricePerNight?.currency,
        lowestAverageDormPricePerNightDiscount = lowestAverageDormPricePerNight?.promotions?.totalDiscount,
        lowestAverageDormPricePerNightOriginal = lowestAverageDormPricePerNight?.original,

        isFreeCancellation = freeCancellationAvailable,
        isFeatured = isFeatured,
        isPromoted = isPromoted,
        isNew = isNew,
        isRecommended = hostelworldRecommends,
        city = locationResponse ?: "No location found",
        overview = overview
    )
}

fun PropertyEntity.toProperty(images: List<String>?): Property {
    val labelList: MutableList<PropertyHighlights> = mutableListOf()
    if (isFeatured) labelList.add(PropertyHighlights.FEATURED)
    if (isNew) labelList.add(PropertyHighlights.NEW)
    if (isPromoted) labelList.add(PropertyHighlights.PROMOTED_PROPERTY)
    if (isFreeCancellation) labelList.add(PropertyHighlights.FREE_CANCELLATION)
    return Property(
        propertyId = propertyId,
        name = name,
        ratingOverall = ratingOverall,
        numberOfRatings = numberOfRatings,
        type = PropertyType.values()[type],
        distance = distance,
        distanceUnit = distanceUnit,

        lowestPricePerNightValue = lowestPricePerNightValue,
        lowestPricePerNightCurrency = lowestPricePerNightCurrency,
        lowestPrivatePricePerNightValue = lowestPrivatePricePerNightValue,
        lowestPrivatePricePerNightCurrency = lowestPrivatePricePerNightCurrency,
        lowestDormPricePerNightValue = lowestDormPricePerNightValue,
        lowestDormPricePerNightCurrency = lowestDormPricePerNightCurrency,

        lowestAveragePrivatePricePerNightValue = lowestAveragePrivatePricePerNightValue,
        lowestAveragePrivatePricePerNightCurrency = lowestAveragePrivatePricePerNightCurrency,
        lowestAveragePrivatePricePerNightDiscount = lowestAveragePrivatePricePerNightDiscount,
        lowestAveragePrivatePricePerNightOriginal = lowestAveragePrivatePricePerNightOriginal,

        lowestAverageDormPricePerNightValue = lowestAverageDormPricePerNightValue,
        lowestAverageDormPricePerNightCurrency = lowestAverageDormPricePerNightCurrency,
        lowestAverageDormPricePerNightDiscount = lowestAverageDormPricePerNightDiscount,
        lowestAverageDormPricePerNightOriginal = lowestAverageDormPricePerNightOriginal,

        labelList = labelList,
        images = images ?: emptyList(),
        city = city,
        overview = overview
    )
}