package com.example.hwapp.domain.api.repository

import com.example.hwapp.common.Resource
import io.reactivex.rxjava3.core.Observable

interface NetworkStatsApiRepo {

    suspend fun getNetworkStats(
        action: String,
        duration: Long
    ): Observable<Resource<Int>>
}