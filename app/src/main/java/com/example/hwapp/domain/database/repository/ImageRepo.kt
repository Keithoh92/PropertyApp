package com.example.hwapp.domain.database.repository

import com.example.hwapp.data.database.entity.ImageEntity

interface ImageRepo {

    suspend fun fetchImagesBy(propertyId: Long): List<ImageEntity>

    suspend fun fetchAllPropertyIds(): List<Long>

    suspend fun exists(propertyId: Long): Int

    suspend fun upsert(images: List<ImageEntity>)

    suspend fun deleteAll()
}