package com.example.mytab.tabs


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication
import com.example.mytab.MainApplication.Companion.VIDEO_DATA
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractActivity
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData
import com.example.mytab.models.VideoItem
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
import java.text.SimpleDateFormat
import java.util.Date

class PlayerActivity : AbstractActivity() , YouTubePlayerListener{

    lateinit var recyclerView: RecyclerView
    lateinit var youtubePlayerView: YouTubePlayerView
    var adapter : YoutubeAdapter? = null
    lateinit var videoData : VideoItem
    lateinit var searchData : SearchData
    var ytList = ArrayList<VideoItem>()
    var youTubePlayer : YouTubePlayer? = null
    lateinit var dwnButton: Button
    lateinit var playlistButton: Button
    lateinit var title: TextView
    val currentDate = Date()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formatDate = dateFormat.format(currentDate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        setContentView(com.example.mytab.R.layout.youtube_player)
        youtubePlayerView = findViewById(com.example.mytab.R.id.youTubePlayerView)

        dwnButton = findViewById(R.id.download_btn)
        playlistButton = findViewById(R.id.add_playl_btn)

        Log.d(ContentValues.TAG, "onCreateView: video list on the player");


        // screen for our youtube player view.
//        youtubePlayerView.enterFullScreen()
//        youtubePlayerView.toggleFullScreen()

        // for our youtube player view.
        lifecycle.addObserver(youtubePlayerView)

        dwnButton.setOnClickListener {

            MainApplication.downloadList.add(videoData)

            var gson = Gson()
            var jsonString = gson.toJson(MainApplication.downloadList)
            val editor = MainApplication.sharePreference?.edit()
            editor?.putString("dwn_videos", jsonString)
            editor?.commit()
            val json = MainApplication.sharePreference!!.getString("dwn_videos", "[]")
            println("DWN VIDEOS : " + json)
//            Toast.makeText(this@PlayerActivity, "click and saved video on shared", Toast.LENGTH_SHORT).show()
        }

        playlistButton.setOnClickListener {

            MainApplication.playList.add(videoData)
            println("we have a playlistDDDD")
//            showPlaylistDialog()

            var gson = Gson()
            var jsonString = gson.toJson(MainApplication.playList)
            val editorPlay = MainApplication.playSharePreference?.edit()
            editorPlay?.putString("playlistName", "TESTPLAY")
            editorPlay?.putString("playlistCreateDate", formatDate)
            editorPlay?.putString("songs", jsonString)
            editorPlay?.commit()
            val jsonSong = MainApplication.playSharePreference?.getString("songs", "[]")
            println("created songs list at playlistOOOO" + jsonSong)
            Toast.makeText(this@PlayerActivity, "click and saved video on playlist shared", Toast.LENGTH_SHORT).show()

        }

        recyclerView = findViewById(com.example.mytab.R.id.sub_yt_recycler_view)
        Log.d(ContentValues.TAG, "onCreateView: started videoPlayer ");
        val service = retrofitYTList.create(ServiceInterface::class.java)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = YoutubeAdapter(ytList , this)

//        adapter = YoutubeAdapter(ytList, object : YoutubeListener{
//            override fun clickAtPosition(position: Int, data: VideoItem) {
//

//
//            }
//
//            override fun clickAtPlaylistPosition(position: Int, data: SongItem) {
//                TODO("Not yet implemented")
//            }
//        })

        recyclerView.adapter = adapter

        val bundle:Bundle? = intent.extras
        videoData = bundle!!.getSerializable(VIDEO_DATA) as VideoItem
        val key = bundle!!.getString("KEY")

        youtubePlayerView.addYouTubePlayerListener(this)

        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", videoData.snippet!!.title!!, "video", "snippet").enqueue(object :
            Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                println(" YoutubeListActivity XOXO 3 with player")
//                try {
                    ytList.addAll(response.body()!!.itemsArray!!)
                    adapter!!.notifyDataSetChanged()
//                } catch (e: Exception) {
//                    println("ExceptionDD" + e)
//                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("XOXO 3")
            }

        })

    }


    public fun showPlaylistDialog(){


        println("it is showPlaylistDialogoo")

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.playlist_title)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinner_playlist_titles)

        with(builder) {
            setTitle("New playlist title")
            setPositiveButton("Create"){ dialog, which ->
//                title.text = editText.text.toString()

//                MainApplication.playList.add(videoData)


            }

            setNegativeButton("Cancel"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
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

    override fun clickAtPosition(position: Int, data: VideoItem) {
        super.clickAtPosition(position, data)

        videoData = data
        youTubePlayer!!.loadVideo(videoData.id!!.videoId!!,0F)

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
