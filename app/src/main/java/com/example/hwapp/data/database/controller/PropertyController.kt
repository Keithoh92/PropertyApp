package com.example.hwapp.data.database.controller

import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.data.database.entity.PropertyEntity
import com.example.hwapp.domain.database.repository.ImageRepo
import com.example.hwapp.domain.database.repository.PropertyDBRepo
import com.example.hwapp.domain.mapper.toProperty
import com.example.hwapp.domain.model.Property
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PropertyController(
    private val propertyRepository: PropertyDBRepo,
    private val imageRepository: ImageRepo
) {

    suspend fun fetchAllProperties(): List<Property> = withContext(Dispatchers.IO) {
        val propertyEntities = propertyRepository.fetchAllProperties()
        return@withContext propertyEntities.map {
            val propertyImages = fetchImagesBy(it.propertyId)
            it.toProperty(propertyImages)
        }
    }

    suspend fun fetchPropertyBy(propertyId: Long): Property = withContext(Dispatchers.IO) {
        val property = propertyRepository.fetchProperty(propertyId)
        val propertyImages = fetchImagesBy(propertyId)
        return@withContext property.toProperty(propertyImages)
    }

    private suspend fun fetchImagesBy(propertyId: Long): List<String> = withContext(Dispatchers.IO) {
        val imageEntities = imageRepository.fetchImagesBy(propertyId)
        val imageUrls = mutableListOf<String>()
        imageEntities.forEach {
            imageUrls.add("${it.prefix}${it.suffix}")
        }
        return@withContext imageUrls
    }

    suspend fun upsertProperties(properties: List<PropertyEntity>) = withContext(Dispatchers.IO) {
        return@withContext propertyRepository.upsert(properties)
    }

    suspend fun upsertImages(images: List<ImageEntity>) = withContext(Dispatchers.IO) {
        val imageList = mutableListOf<ImageEntity>()
        imageList.addAll(images)
        val propertyIds = images.distinctBy { it.propertyId }.map { it.propertyId }
        propertyIds.forEach { propertyId ->
            if (imageRepository.exists(propertyId) > 0) {
                imageList.removeIf { it.propertyId == propertyId }
            }
        }

        return@withContext imageRepository.upsert(imageList)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        propertyRepository.deleteAll()
        imageRepository.deleteAll()
    }
}