package com.goodayedi.asteroid.network

import com.goodayedi.asteroid.BuildConfig
import com.goodayedi.asteroid.domain.PictureOfTheDay
import retrofit2.Retrofit
import retrofit2.http.GET

private const val API_KEY = BuildConfig.NASA_API_KEY
private const val BASE_URL = BuildConfig.NASA_BASE_URL
private const val NEO_FEED = BuildConfig.NASA_NEO_FEED
private const val IMAGE_OF_THE_DAY = BuildConfig.IMAGE_OF_THE_DAY

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarOrJsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NasaApiService {
    @Scalar
    @GET("$NEO_FEED?start_date=2015-09-07&end_date=2015-09-08&api_key=$API_KEY")
    suspend fun getAsteroids(): String
    @Json
    @GET("$IMAGE_OF_THE_DAY?api_key=$API_KEY")
    suspend fun getPictureOfTheDay(): PictureOfTheDay
}

object Nasa {
    val api: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}