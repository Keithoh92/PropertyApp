package com.example.hwapp.view.propertyListing.effect

sealed class PropertyListingEffect {
    sealed class Navigation : PropertyListingEffect() {
        data class PropertyDetailsScreen(val propertyId: Long) : Navigation()
    }
}