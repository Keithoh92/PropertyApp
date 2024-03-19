package com.example.hwapp.data.api.remote

import com.example.hwapp.data.api.dto.ExchangeRateDTO
import retrofit2.http.GET

interface ExchangeRateAPI {
    @GET("getExchangeRates")
    suspend fun getExchangeRates(): List<ExchangeRateDTO>
}