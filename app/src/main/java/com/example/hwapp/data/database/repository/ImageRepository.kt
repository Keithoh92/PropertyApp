package com.example.hwapp.data.database.repository

import com.example.hwapp.data.database.dao.ImageDao
import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.domain.database.repository.ImageRepo

class ImageRepository(
    private val imageDao: ImageDao
) : ImageRepo {

    override suspend fun fetchImagesBy(propertyId: Long): List<ImageEntity> =
        imageDao.fetchImagesBy(propertyId)

    override suspend fun fetchAllPropertyIds(): List<Long> = imageDao.fetchPropertyIds()

    override suspend fun exists(propertyId: Long): Int = imageDao.exists(propertyId)

    override suspend fun upsert(images: List<ImageEntity>) = imageDao.upsert(images)

    override suspend fun deleteAll() = imageDao.deleteAll()
}