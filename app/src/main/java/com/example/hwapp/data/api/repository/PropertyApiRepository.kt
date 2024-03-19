package com.example.hwapp.data.api.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import com.example.hwapp.common.Resource
import com.example.hwapp.data.api.NetworkRequestTracker
import com.example.hwapp.data.database.entity.ImageEntity
import com.example.hwapp.data.database.entity.PropertyEntity
import com.example.hwapp.domain.api.repository.NetworkStatsApiRepo
import com.example.hwapp.domain.api.repository.PropertyApiRepo
import com.example.hwapp.domain.mapper.toImageEntity
import com.example.hwapp.domain.mapper.toPropertyEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PropertyApiRepository(
    private val networkRequestTracker: NetworkRequestTracker,
    private val networkStatsAPIRepository: NetworkStatsApiRepo
) : PropertyApiRepo {

    override suspend fun getProperties(
        page: Int,
        pageCount: Int,
    ): Observable<Resource<Triple<List<PropertyEntity>, List<ImageEntity>, Long>>> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(Resource.Loading())

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val (response, duration) = networkRequestTracker.trackGetProperties(page, pageCount)
                        val stats = getNetworkStats(duration)
                        Log.d(TAG, "NetworkStats - Request took = $stats seconds")
                        val responseBody = response.body()
                        val locationResponse = responseBody?.location?.city?.name
                        val propertiesResponse = responseBody?.properties
                        var images: MutableList<ImageEntity> = mutableListOf()
                        val properties: MutableList<PropertyEntity> = mutableListOf()
                        propertiesResponse?.forEach { property ->
                            property.imageDTOGallery.forEach {
                                images.add(it.toImageEntity(property.id))
                            }
                            properties.add(property.toPropertyEntity(locationResponse))
                        }

                        emitter.onNext(Resource.Success(Triple(properties.toList(), images.toList(), duration)))
                        emitter.onComplete()
                    } catch (e: IOException) {
                        emitter.onError(Throwable("Couldn't reach server, check internet connection: ${e.message}"))
                        Log.e(TAG, "Couldn't reach server, check internet connection: ${e.message}")
                    } catch (e: HttpException) {
                        emitter.onError(Throwable(e.localizedMessage ?: "Http Exception, unexpected response: ${e.code()} ${e.message}"))
                        Log.e(TAG, "Http Exception, unexpected response: ${e.code()} ${e.message}")
                    }
                }
            } catch (e: IOException) {
                emitter.onError(Throwable("Couldn't reach server, check internet connection: ${e.message}"))
                Log.e(TAG, "Couldn't reach server, check internet connection: ${e.message}")
            } catch (e: HttpException) {
                emitter.onError(Throwable(e.localizedMessage ?: "Http Exception, unexpected response: ${e.code()} ${e.message}"))
                Log.e(TAG, "Http Exception, unexpected response: ${e.code()} ${e.message}")
            }
        }.subscribeOn(Schedulers.io())
    }

    @SuppressLint("CheckResult")
    suspend fun getNetworkStats(duration: Long) {
        networkStatsAPIRepository.getNetworkStats(
            action = "https://gist.githubusercontent.com/PedroTrabulo-\n" +
                    "Hostelworld/a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563\n" +
                    "138541/properties.json",
            duration = duration
        )
            .observeOn(Schedulers.io())
            .subscribe({ result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d(TAG, "NetworkStats - Loading")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "NetworkStats - Request took = ${result.data} seconds")
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "NetworkStats - Error - ${result.message}")
                    }
                }
            }, { error ->
                Log.e(TAG, "Error: ${error.message}")
            })
    }
}