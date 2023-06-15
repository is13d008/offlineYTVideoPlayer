package com.example.mytab.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {



    val logging = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>) : T{
        return retrofit.create(service)
    }

}