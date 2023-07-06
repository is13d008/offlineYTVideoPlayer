package com.example.mytab.tabs

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication
import com.example.mytab.MainApplication.Companion.VIDEO_DATA
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractActivity
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.PlaylistItem
import com.example.mytab.models.SearchData
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofitYTList
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubeListActivity : AbstractActivity() {

    lateinit var recyclerView: RecyclerView
    var ytList = ArrayList<VideoItem>()
    lateinit var adapter : YoutubeAdapter
    lateinit var searchData : SearchData
    lateinit var videoItemClk: CardView
    var listType : String = ""
//    var playlistIndex : Int = 0
    lateinit var respons : PlaylistItem
    var playlistIndex : Int = 0

    override fun onCreate( savedInstanceState: Bundle? ) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_list)

        val bundle:Bundle? = intent.extras
        listType = bundle!!.getString("LIST_TYPE","")

        recyclerView = findViewById(R.id.video_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = YoutubeAdapter(ytList , this)
        recyclerView.adapter = adapter

        if (listType == MainApplication.LIST_TYPE_SONGS){
            searchData = bundle!!.getSerializable(MainApplication.VIDEO_DATA) as SearchData

            val service = retrofitYTList.create(ServiceInterface::class.java)
            service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", searchData.searchName!!, "video", "snippet").enqueue(object : Callback<ApiResponse> {

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    println(" YoutubeListActivity XOXO 1")
                    ytList.addAll(response.body()!!.itemsArray!!)
                    adapter.notifyDataSetChanged()

                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    println("XOXO 2")
                }
            })


        } else {

            val bundle:Bundle? = intent.extras
            respons = bundle!!.getSerializable(MainApplication.PLAYLIST_DATA) as PlaylistItem
            ytList.addAll(respons.songs)
            adapter.notifyDataSetChanged()

        }

    }

    override fun clickAtPosition(position: Int, data: Any) {
        super.clickAtPosition(position, data)

        val intent = Intent(this@YoutubeListActivity, PlayerActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(VIDEO_DATA,data as VideoItem)
        bundle.putString("KEY","qwerty")
        intent.putExtras(bundle)
        startActivity(intent)

    }
}