package com.example.mytab.tabs

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication.Companion.SINGER_NAME
import com.example.mytab.MainApplication.Companion.VIDEO_DATA
import com.example.mytab.R
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofitYTList
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubeListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var ytList = ArrayList<VideoItem>()
    lateinit var adapter : YoutubeAdapter
    lateinit var searchData : SearchData
    lateinit var videoItemClk: CardView

    override fun onCreate( savedInstanceState: Bundle? ) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_list)

        recyclerView = findViewById(R.id.video_recycler_view)

        Log.d(ContentValues.TAG, "onCreateView: started");

        val service = retrofitYTList.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)


//        videoItemClk = findViewById(R.id.video_card_view)

//        videoItemClk.setOnClickListener {
//            val intent = Intent(this@YoutubeListActivity, PlayerActivity::class.java)
////                intent.putExtra(VIDEO_ID, data)
//                startActivity(intent)
//        }



        adapter = YoutubeAdapter(ytList, object : YoutubeListener{
            override fun clickAtPosition(position: Int, data: VideoItem, chosenName: SearchData) {

                println("videoIDD" + chosenName + "Data-> " + data)

                val intent = Intent(this@YoutubeListActivity, PlayerActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable(VIDEO_DATA,data)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        })


        recyclerView.adapter = adapter


        val bundle:Bundle? = intent.extras
        searchData = bundle!!.getSerializable(SINGER_NAME) as SearchData

        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", searchData.searchName!!, "video", "snippet").enqueue(object : Callback<ApiResponse> {

//            _isLoading.value = true
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                _isLoading.value = false
                println(" YoutubeListActivity XOXO 1")
                ytList.addAll(response.body()!!.itemsArray!!)
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("XOXO 2")
//                _isLoading.value = false
            }
        })

    }
}