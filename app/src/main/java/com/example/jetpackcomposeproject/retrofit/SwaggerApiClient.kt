package com.example.jetpackcomposeproject.retrofit

import com.example.jetpackcomposeproject.BuildConfig.BASE_URL
import com.example.jetpackcomposeproject.BuildConfig.SWAGGER_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SwaggerApiClient {
    private val retrofitJsonInstance: Retrofit.Builder by lazy {
        val httpIntercept = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(httpIntercept)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(20, TimeUnit.SECONDS)
        }.build()
        Retrofit.Builder()
            .baseUrl(SWAGGER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }
    val apiInterfaces: ApiService by lazy {
        retrofitJsonInstance.build().create(ApiService::class.java)
    }
}