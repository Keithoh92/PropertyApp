package com.example.hwapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hwapp.data.database.entity.ImageEntity

@Dao
interface ImageDao {

    @Query("SELECT * FROM image WHERE propertyId = :propertyId")
    fun fetchImagesBy(propertyId: Long): List<ImageEntity>

    @Query("SELECT propertyId FROM image")
    fun fetchPropertyIds(): List<Long>

    @Query("SELECT count(*) FROM image WHERE propertyId = :propertyId")
    fun exists(propertyId: Long): Int

    @Upsert
    fun upsert(images: List<ImageEntity>)

    @Query("DELETE FROM image")
    fun deleteAll()
}