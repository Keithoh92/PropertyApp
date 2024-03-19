package com.example.hwapp.di

import androidx.room.Room
import com.example.hwapp.PropertyApplication
import com.example.hwapp.data.api.remote.NetworkStatsAPI
import com.example.hwapp.data.database.PropertyDatabase
import com.example.hwapp.data.database.dao.ImageDao
import com.example.hwapp.data.database.dao.PropertyDAO
import com.example.hwapp.data.api.remote.PropertyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(): PropertyDatabase {
        return Room.databaseBuilder(
            PropertyApplication.appContext,
            PropertyDatabase::class.java,
            "PROPERTY_DB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePropertyApi(): PropertyApi {
        return Retrofit.Builder()
            .baseUrl(PropertyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(PropertyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkStatsApi(): NetworkStatsAPI {
        return Retrofit.Builder()
            .baseUrl(PropertyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(NetworkStatsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePropertyDao(database: PropertyDatabase): PropertyDAO {
        return database.propertyDao()
    }

    @Provides
    @Singleton
    fun provideImageDao(database: PropertyDatabase): ImageDao {
        return database.imageDao()
    }

}