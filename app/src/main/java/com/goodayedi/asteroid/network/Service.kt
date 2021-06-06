package com.goodayedi.asteroid.network

import com.goodayedi.asteroid.BuildConfig
import com.goodayedi.asteroid.model.PictureOfTheDay
import com.goodayedi.asteroid.utils.getNextSevenDaysFormattedDates
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val API_KEY = BuildConfig.NASA_API_KEY
private const val BASE_URL = BuildConfig.NASA_BASE_URL
private const val NEO_FEED = BuildConfig.NASA_NEO_FEED
private const val IMAGE_OF_THE_DAY = BuildConfig.IMAGE_OF_THE_DAY

private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

val httpClient: OkHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(interceptor)
    .connectTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor() {
        val original = it.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        it.proceed(request)
    }.build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarOrJsonConverterFactory.create())
    .client(httpClient)
    .baseUrl(BASE_URL)
    .build()

interface NasaApiService {
    @Scalar
    @GET(NEO_FEED)
    suspend fun getAsteroids(@Query("start_date") date: String): String

    @Json
    @GET(IMAGE_OF_THE_DAY)
    suspend fun getPictureOfTheDay(): PictureOfTheDay
}

object Nasa {
    val api: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}