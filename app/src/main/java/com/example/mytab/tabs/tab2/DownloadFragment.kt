package com.example.mytab.tabs.tab2

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.models.ApiResponse
import com.example.mytab.retrofitYTList
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DownloadFragment : AbstractListFragment() {

    private lateinit var recyclerView: RecyclerView

    private var dwnList = ArrayList<ApiResponse>()
    private lateinit var adapter: YoutubeAdapter


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_download, container, false)

        recyclerView = mainView.findViewById(R.id.download_recycler_view)

        Log.d(ContentValues.TAG, "onCreateView: started");

        val service = retrofitYTList.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        adapter = YoutubeAdapter(dwnList)
        recyclerView.adapter = adapter

        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", "jj lin", "video", "snippet").enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                println("XOXO 1")
                println(response.body())
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("XOXO 2")

            }
        })

        return mainView
    }
}