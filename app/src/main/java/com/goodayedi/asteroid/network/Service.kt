package com.goodayedi.asteroid.network

import com.goodayedi.asteroid.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val API_KEY = BuildConfig.NASA_API_KEY
private const val BASE_URL = BuildConfig.NASA_BASE_URL
private const val NEO_FEED = BuildConfig.NASA_NEO_FEED

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NasaApiService {
    @GET("$NEO_FEED?start_date=2015-09-07&end_date=2015-09-08&api_key=$API_KEY")
    suspend fun getAsteroids(): String
}

object Nasa {
    val api: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}