package com.example.hwapp.domain.api.repository

import com.example.hwapp.common.Resource
import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.data.database.entity.PropertyEntity
import io.reactivex.rxjava3.core.Observable

interface PropertyApiRepo {

    suspend fun getProperties(
        page: Int,
        pageCount: Int,
    ): Observable<Resource<Triple<List<PropertyEntity>, List<ImageEntity>, Long>>>
}