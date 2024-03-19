package com.example.hwapp.data.api.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkStatsAPI {

    @GET("https://gist.githubusercontent.com/PedroTrabulo-\n" +
            "Hostelworld/6bed011203c6c8217f0d55f74ddcc5c5/raw/ce8f55cfd963aeef70f2ac9f88f34cefd19fca\n" +
            "30/stats")
    suspend fun getNetworkStats(
        @Query("action") action: String,
        @Query("duration") duration: Long
    ): Response<Int>
}