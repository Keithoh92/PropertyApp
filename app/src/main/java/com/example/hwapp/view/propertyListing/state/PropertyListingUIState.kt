package com.example.hwapp.view.propertyListing.state

import com.example.hwapp.data.api.dto.PropertyDTO
import com.example.hwapp.domain.model.Property

data class PropertyListingUIState(
    val properties: List<Property> = emptyList(),
    val images: List<PropertyDTO.ImageDTO> = emptyList(),
    val loading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
)