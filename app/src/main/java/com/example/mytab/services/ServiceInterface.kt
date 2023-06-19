package com.example.mytab.services

import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData
import com.example.mytab.retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceInterface {

    @Headers("Content-Type:application/json")
    @GET("https://www.googleapis.com/youtube/v3/search")
    fun getAllVideos(
        @Query("key") key : String,
        @Query("q") q : String,
        @Query("type") type : String,
        @Query("part") part : String,
    ): Call<ApiResponse>

    @Headers("Content-Type:application/json")
    @GET("https://suggestqueries.google.com/complete/search")
    fun getAllSuggests(
        @Query("client") key : String,
        @Query("ds") type : String,
        @Query("q") q : String,
    ): Call<Any>

}