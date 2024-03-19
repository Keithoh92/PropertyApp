package com.example.hwapp.data.database.repository

import com.example.hwapp.data.database.dao.PropertyDAO
import com.example.hwapp.data.database.entity.PropertyEntity
import com.example.hwapp.domain.database.repository.PropertyDBRepo

class PropertyDBRepository(
    private val propertyDAO: PropertyDAO
) : PropertyDBRepo {

    override suspend fun fetchAllProperties(): List<PropertyEntity> =
        propertyDAO.fetchAllProperties()

    override suspend fun fetchProperty(propertyId: Long): PropertyEntity =
        propertyDAO.fetchPropertyBy(propertyId)

    override suspend fun propertiesExist(): Boolean = propertyDAO.propertiesExist() > 0

    override suspend fun upsert(properties: List<PropertyEntity>) {
        propertyDAO.upsertAll(properties)
    }

    override suspend fun deleteAll() = propertyDAO.deleteAll()
}