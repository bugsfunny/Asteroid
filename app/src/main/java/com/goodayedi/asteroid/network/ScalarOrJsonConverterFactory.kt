package com.goodayedi.asteroid.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type

class ScalarOrJsonConverterFactory : Converter.Factory() {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val scalar: Converter.Factory = ScalarsConverterFactory.create()
    private val json: Converter.Factory = MoshiConverterFactory.create(moshi)

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        annotations.forEach { annotation ->
            when (annotation) {
                is Scalar -> return scalar.responseBodyConverter(type, annotations, retrofit)
                is Json -> return json.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return json.responseBodyConverter(type, annotations, retrofit)
    }

    companion object {
        fun create() = ScalarOrJsonConverterFactory()
    }
}