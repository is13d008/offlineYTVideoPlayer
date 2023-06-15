package com.example.mytab

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {

    companion object {

        var retrofit : Retrofit? = null

    }

    override fun onCreate() {
        super.onCreate()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

}

val retrofit : Retrofit by lazy {
    MainApplication.retrofit!!
}