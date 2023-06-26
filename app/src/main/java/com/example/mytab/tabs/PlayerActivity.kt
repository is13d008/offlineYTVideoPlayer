package com.example.mytab.tabs

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication.Companion.VIDEO_DATA
import com.example.mytab.R
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData
import com.example.mytab.models.VideoItem
import com.example.mytab.models.videoId
import com.example.mytab.retrofitYTList
import com.example.mytab.services.ServiceInterface
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlayerActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var youtubePlayerView: YouTubePlayerView
    lateinit var adapter : YoutubeAdapter
    lateinit var videoData : VideoItem
    lateinit var searchData : SearchData

//    var ytList = ArrayList<VideoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        setContentView(com.example.mytab.R.layout.youtube_player)
        youtubePlayerView = findViewById(com.example.mytab.R.id.youTubePlayerView)

        Log.d(ContentValues.TAG, "onCreateView: video list on the player");

        // screen for our youtube player view.
//        youtubePlayerView.enterFullScreen()
//        youtubePlayerView.toggleFullScreen()

        // for our youtube player view.
        lifecycle.addObserver(youtubePlayerView)


        recyclerView = findViewById(com.example.mytab.R.id.sub_yt_recycler_view)
        Log.d(ContentValues.TAG, "onCreateView: started videoPlayer ");
        val service = retrofitYTList.create(ServiceInterface::class.java)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val bundle:Bundle? = intent.extras
        videoData = bundle!!.getSerializable(VIDEO_DATA) as VideoItem
        println("Player dataIDD" + videoData)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoData.id!!.videoId!!, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
            }
        })


//        val bundle:Bundle? = intent.extras
//        searchData = bundle!!.getSerializable(MainApplication.SINGER_NAME) as SearchData
//
//        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", "jj lin", "video", "snippet").enqueue(object :
//            Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                println(" YoutubeListActivity XOXO 1 with player")
//                ytList.addAll(response.body()!!.itemsArray!!)
//                adapter.notifyDataSetChanged()
//
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                println("XOXO 2")
//
//            }
//        })



    }

}