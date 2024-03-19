package com.example.hwapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hwapp.data.database.dao.ImageDao
import com.example.hwapp.data.database.dao.PropertyDAO
import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.data.database.entity.PropertyEntity

@Database(
    entities = [
        PropertyEntity::class,
        ImageEntity::class
    ],
    version = 12,
    exportSchema = false
)
abstract class PropertyDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDAO
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: PropertyDatabase? = null

        fun getDatabase(context: Context): PropertyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PropertyDatabase::class.java,
                    "property_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}