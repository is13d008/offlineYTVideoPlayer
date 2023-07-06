package com.example.mytab

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mytab.models.PlaylistItem
import com.example.mytab.models.VideoItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainApplication : Application() {

    companion object {

        var retrofit : Retrofit? = null
        var retrofitYTList : Retrofit? = null

        val VIDEO_DATA = "items"

        val LIST_TYPE_SONGS = "songs"
        val LIST_TYPE_PLAYLIST = "playlist"

        val PLAYLIST_DATA = "playlistItems"


        var downloadList = ArrayList<VideoItem>()
        var playList = ArrayList<PlaylistItem>()
        var sharePreference: SharedPreferences? = null
        var playSharePreference: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        sharePreference = getSharedPreferences( getPackageName() + "MY_DOWN_VIDEO", Context.MODE_PRIVATE);
        playSharePreference = getSharedPreferences( getPackageName() + "MY_PLAYLISTS", Context.MODE_PRIVATE);

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder().baseUrl("https://suggestqueries.google.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

        retrofitYTList = Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    }

}

val retrofit : Retrofit by lazy {
    MainApplication.retrofit!!
}

val retrofitYTList : Retrofit by lazy {
    MainApplication.retrofitYTList!!
}