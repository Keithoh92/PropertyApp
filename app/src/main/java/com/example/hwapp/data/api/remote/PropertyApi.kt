package com.example.hwapp.data.api.remote

import com.example.hwapp.data.api.dto.PropertyApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyApi {

    @GET("PedroTrabulo-\n" +
            "Hostelworld/a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563\n" +
            "138541/properties.json")
    suspend fun getProperties(
        @Query("page") page: Int,
        @Query("pageCount") pageCount: Int,
        @Query("duration") duration: Long
    ): Response<PropertyApiResponse>

    companion object {
        const val BASE_URL = " https://gist.githubusercontent.com/"
    }
}