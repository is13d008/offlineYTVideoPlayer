package com.example.mytab.tabs

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractActivity
import com.example.mytab.adapter.PlaylistAdapter
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.PlaylistData
import com.example.mytab.models.SongItem
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofitYTList
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePlaylistActivity : AbstractActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PlaylistAdapter
    var songList = ArrayList<SongItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_playlist)

        recyclerView = findViewById(R.id.playlist_song_recycler)

        Log.d(ContentValues.TAG, "onCreatView Playlist suggest started")

        val service = retrofitYTList.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PlaylistAdapter(songList , this)

//        adapter = PlaylistAdapter(songList, object: YoutubeListener {
//            override fun clickAtPosition(position: Int, data: VideoItem) {
//
//            }
//
//            override fun clickAtPlaylistPosition(position: Int, data: SongItem) {
//
//                println("videoIDD"  + "Data-> " + data)
//
//            }
//        }
//
//        )

        recyclerView.adapter = adapter

        service.getAllPlaylists("snippet", "UC_x5XG1OV2P6uZZ5FSM9Ttw", 50, "AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk").enqueue(object :
            Callback<PlaylistData> {


            override fun onResponse(call: Call<PlaylistData>, response: Response<PlaylistData>) {

                println("getAllPlaylists 1")
                println(" Youtube PlaylistData" + songList)
                songList.addAll(response.body()!!.songsArray!!)
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<PlaylistData>, t: Throwable) {
                println("getAllPlaylists 2")

            }

        })

    }

    override fun clickAtPlaylistPosition(position: Int, data: SongItem) {
        super.clickAtPlaylistPosition(position, data)

    }
}