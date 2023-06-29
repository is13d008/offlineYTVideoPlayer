package com.example.mytab.tabs

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.mytab.adapter.DownloadAdapter

class PlayerActivity : AppCompatActivity() , YouTubePlayerListener{

    lateinit var recyclerView: RecyclerView
    lateinit var youtubePlayerView: YouTubePlayerView
    var adapter : YoutubeAdapter? = null
    lateinit var videoData : VideoItem
    lateinit var searchData : SearchData
    var ytList = ArrayList<VideoItem>()
    var youTubePlayer : YouTubePlayer? = null
    lateinit var dwnButton: Button
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

        dwnButton = findViewById(R.id.download_btn)

        // screen for our youtube player view.
//        youtubePlayerView.enterFullScreen()
//        youtubePlayerView.toggleFullScreen()

        // for our youtube player view.
        lifecycle.addObserver(youtubePlayerView)

        dwnButton.setOnClickListener {
//            var dlgBuilder = AlertDialog.Builder(this)
//
//            dlgBuilder.setTitle(R.string.dialogTitle)
//            dlgBuilder.setMessage(R.string.dialogMessage)
//            dlgBuilder.setIcon(R.drawable.baseline_check_circle_24)
            Toast.makeText(this@PlayerActivity, "clicked yes", Toast.LENGTH_SHORT).show()

            val sharePreferences = getSharedPreferences("MY_LIST_DATA", Context.MODE_PRIVATE)
//                ?: return
//            with(sharePreferences.edit()) {
//                putString(getString("VIDEO_DATA"), DownloadAdapter)
//                apply()
//            }
//            fun onClick(view: View) {
//                Gson gson = new Gson()
//            }


        }

        recyclerView = findViewById(com.example.mytab.R.id.sub_yt_recycler_view)
        Log.d(ContentValues.TAG, "onCreateView: started videoPlayer ");
        val service = retrofitYTList.create(ServiceInterface::class.java)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = YoutubeAdapter(ytList, object : YoutubeListener{
            override fun clickAtPosition(position: Int, data: VideoItem) {

                videoData = data
                youTubePlayer!!.loadVideo(videoData.id!!.videoId!!,0F)

                println("videoIDD"  + "Data-> " + data)

            }
        })


        recyclerView.adapter = adapter

        val bundle:Bundle? = intent.extras
        videoData = bundle!!.getSerializable(VIDEO_DATA) as VideoItem
        val key = bundle!!.getString("KEY")
        println("Player dataIDD" + videoData)

        youtubePlayerView.addYouTubePlayerListener(this)


//        val bundle:Bundle? = intent.extras
//        searchData = bundle!!.getSerializable(MainApplication.SINGER_NAME) as SearchData
//
        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", videoData.snippet!!.title!!, "video", "snippet").enqueue(object :
            Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                println(" YoutubeListActivity XOXO 3 with player")
//                try {
                    ytList.addAll(response.body()!!.itemsArray!!)
                    adapter!!.notifyDataSetChanged()
                    println("nemeltList" + ytList)

//                } catch (e: Exception) {
//                    println("ExceptionDD" + e)
//                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("XOXO 3")

            }
        })



    }


    override fun onApiChange(youTubePlayer: YouTubePlayer) {
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
    }

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
    }

    override fun onPlaybackQualityChange(
        youTubePlayer: YouTubePlayer,
        playbackQuality: PlayerConstants.PlaybackQuality
    ) {
    }

    override fun onPlaybackRateChange(
        youTubePlayer: YouTubePlayer,
        playbackRate: PlayerConstants.PlaybackRate
    ) {
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {

        this.youTubePlayer = youTubePlayer
        this.youTubePlayer!!.loadVideo(videoData.id!!.videoId!!,0F)

    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
    }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
    }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {
    }

}

private fun AlertDialog.Builder.setPositiveButton(s: String, function: () -> Unit) {

}
