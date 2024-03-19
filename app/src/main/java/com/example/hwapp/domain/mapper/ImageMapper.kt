package com.example.hwapp.domain.mapper

import com.example.hwapp.data.api.dto.PropertyDTO
import com.example.hwapp.data.database.entity.ImageEntity

fun PropertyDTO.ImageDTO.toImageEntity(propertyId: Long): ImageEntity {
    return ImageEntity(
        propertyId = propertyId,
        prefix = prefix,
        suffix = suffix
    )
}