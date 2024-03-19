package com.example.hwapp.view.propertyListing.event

sealed class PropertyListingEvent {
    object OnRefresh : PropertyListingEvent()
    data class OnClickProperty(val propertyId: Long) : PropertyListingEvent()
}