package com.example.hwapp.data.api

import com.example.hwapp.data.api.dto.PropertyApiResponse
import com.example.hwapp.data.api.remote.PropertyApi
import retrofit2.Response

class NetworkRequestTracker(private val propertyAPI: PropertyApi) {
    suspend fun trackGetProperties(page: Int, pageCount: Int): Pair<Response<PropertyApiResponse>, Long> {
        val startTime = System.currentTimeMillis()
        val response = propertyAPI.getProperties(page, pageCount, System.currentTimeMillis() - startTime)
        val duration = System.currentTimeMillis() - startTime
        return Pair(response, duration)
    }
}