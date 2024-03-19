package com.example.hwapp.data.api.repository

import android.content.ContentValues
import android.util.Log
import com.example.hwapp.common.Resource
import com.example.hwapp.data.api.remote.NetworkStatsAPI
import com.example.hwapp.domain.api.repository.NetworkStatsApiRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NetworkStatsAPIRepository(
    private val networkStatsAPI: NetworkStatsAPI
) : NetworkStatsApiRepo {

    override suspend fun getNetworkStats(
        action: String,
        duration: Long
    ): Observable<Resource<Int>> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(Resource.Loading())

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = networkStatsAPI.getNetworkStats(action, duration)
                        val responseBody = response.body()
                        Log.d(ContentValues.TAG, "NetworkStats - Request took = $responseBody seconds")
                        emitter.onNext(Resource.Success(responseBody ?: -1))
                        emitter.onComplete()
                    } catch (e: IOException) {
                        emitter.onError(Throwable("Couldn't reach server, check internet connection: ${e.message}"))
                        Log.e(ContentValues.TAG, "Couldn't reach server, check internet connection: ${e.message}")
                    } catch (e: HttpException) {
                        emitter.onError(Throwable(e.localizedMessage ?: "Http Exception, unexpected response: ${e.code()} ${e.message}"))
                        Log.e(ContentValues.TAG, "Http Exception, unexpected response: ${e.code()} ${e.message}")
                    }
                }
            } catch (e: IOException) {
                emitter.onError(Throwable("Couldn't reach server, check internet connection: ${e.message}"))
                Log.e(ContentValues.TAG, "Couldn't reach server, check internet connection: ${e.message}")
            } catch (e: HttpException) {
                emitter.onError(Throwable(e.localizedMessage ?: "Http Exception, unexpected response: ${e.code()} ${e.message}"))
                Log.e(ContentValues.TAG, "Http Exception, unexpected response: ${e.code()} ${e.message}")
            }
        }.subscribeOn(Schedulers.io())
    }
}