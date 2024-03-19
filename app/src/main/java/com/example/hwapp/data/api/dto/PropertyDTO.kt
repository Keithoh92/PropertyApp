package com.example.hwapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class PropertyDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("overallRating")
    val overallRating: OverallRatingDTO, // overall & numberOfRatings
    @SerializedName("isFeatured")
    val isFeatured: Boolean,
    @SerializedName("isPromoted")
    val isPromoted: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("distance")
    val distance: DistanceDTO,
    @SerializedName("freeCancellationAvailable")
    val freeCancellationAvailable: Boolean,
    @SerializedName("lowestPricePerNight")
    val lowestPricePerNight: PriceDTO?, // value & currency
    @SerializedName("lowestPrivatePricePerNight")
    val lowestPrivatePricePerNight: PriceDTO?, // value & currency
    @SerializedName("lowestDormPricePerNight")
    val lowestDormPricePerNight: PriceDTO?, // value & currency
    @SerializedName("lowestAverageDormPricePerNight")
    val lowestAverageDormPricePerNight: PriceDTO?,
    @SerializedName("lowestAveragePrivatePricePerNight")
    val lowestAveragePrivatePricePerNight: PriceDTO?,
    @SerializedName("isNew")
    val isNew: Boolean,
    @SerializedName("hostelworldRecommends")
    val hostelworldRecommends: Boolean,
    @SerializedName("imagesGallery")
    val imageDTOGallery: List<ImageDTO>,
    @SerializedName("overview")
    val overview: String
) {
    data class OverallRatingDTO(
        @SerializedName("overall")
        val overall: Double,
        @SerializedName("numberOfRatings")
        val numberOfRatings: String
    )

    data class DistanceDTO(
        @SerializedName("value")
        val value: Double,
        @SerializedName("units")
        val units: String
    )

    data class ImageDTO(
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("suffix")
        val suffix: String
    )
}
