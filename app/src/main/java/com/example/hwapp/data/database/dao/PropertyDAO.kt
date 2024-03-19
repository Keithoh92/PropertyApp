package com.example.hwapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hwapp.data.database.entity.PropertyEntity

@Dao
interface PropertyDAO {

    @Query("SELECT * FROM property")
    fun fetchAllProperties(): List<PropertyEntity>

    @Query("SELECT * FROM property WHERE propertyId = :propertyId LIMIT 1")
    fun fetchPropertyBy(propertyId: Long): PropertyEntity

    @Query("SELECT count(*) FROM property")
    fun propertiesExist(): Int

    @Upsert
    fun upsertAll(properties: List<PropertyEntity>)

    @Query("DELETE FROM property")
    fun deleteAll()
}