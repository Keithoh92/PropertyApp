package com.example.hwapp.view.propertyDetails.state

import com.example.hwapp.domain.model.Property
import com.example.hwapp.view.propertyListing.view.components.mockPropertyListing

data class PropertyDetailsUIState(
    val property: Property = mockPropertyListing(),
    val isLoading: Boolean = false
)
