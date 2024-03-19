package com.example.hwapp.domain.database.repository

import com.example.hwapp.data.database.entity.PropertyEntity

interface PropertyDBRepo {

    suspend fun fetchAllProperties(): List<PropertyEntity>

    suspend fun fetchProperty(propertyId: Long): PropertyEntity

    suspend fun propertiesExist(): Boolean

    suspend fun upsert(properties: List<PropertyEntity>)

    suspend fun deleteAll()
}