package com.example.hwapp.domain.enums

enum class RatingHierarchy(val rating: Double) {
    SUPERB(9.0),
    FABULOUS(8.0),
    GREAT(7.0),
    GOOD(6.0),
    DECENT(5.0),
    OKAY(4.0),
    LOW(3.0),
    NO_RATING(0.0);

    companion object {
        @JvmStatic
        @Throws(IllegalArgumentException::class)
        operator fun get(rating: Double): RatingHierarchy {
            return when {
                rating >= SUPERB.rating -> SUPERB
                rating >= FABULOUS.rating -> FABULOUS
                rating >= GREAT.rating -> GREAT
                rating >= GOOD.rating -> GOOD
                rating >= DECENT.rating -> DECENT
                rating >= OKAY.rating -> OKAY
                rating >= LOW.rating -> LOW
                else -> NO_RATING
            }
        }
    }
}