package com.android.apptask.data.remote

import com.android.apptask.BuildConfig
import com.android.apptask.data.models.FruitsResponse
import com.android.data.remote.ApiConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit


@JvmSuppressWildcards
interface WebService {

    @GET("data.json")
    suspend fun getFruits(): Response<FruitsResponse>

    @GET("stats")
    suspend fun sendStats(@QueryMap params: Map<String, Any>): Response<Unit>

    companion object {

        operator fun invoke(headerInterceptor: HeaderInterceptor): WebService {

            val okHttpClint = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(headerInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()

            return Retrofit.Builder()
                .client(okHttpClint)
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }

}

