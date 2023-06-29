package com.example.mytab.tabs.tab2

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.DownloadAdapter
import com.example.mytab.interfaces.DownloadListener
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DownloadFragment : AbstractListFragment() {

    private lateinit var recyclerView: RecyclerView

    private var downloadList = ArrayList<VideoItem>()
    private lateinit var adapter: DownloadAdapter



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_download, container, false)

        recyclerView = mainView.findViewById(R.id.video_recycler_dwnload)

        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        adapter = DownloadAdapter(downloadList, object : DownloadListener {

            override fun savedVideos(position: Int, data: VideoItem) {

//            val intent = Intent(requireActivity(), DownloadListener::class.java)
//            val bundle = Bundle()
            }

        })

        recyclerView. adapter = adapter

        val sharePreferences = activity?.getSharedPreferences("MY_LIST_DATA", Context.MODE_PRIVATE)


        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", "Shakira", "video", "snippet").enqueue(object :
            Callback<ApiResponse> {


            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                println(" Download Activity XOXO 4")
                downloadList.addAll(response.body()!!.itemsArray!!)
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("XOXO ERR 4 DOWNLAOD")
//                _isLoading.value = false
            }
        })


        return mainView
    }

//    private void saveData() {
//
//    }
//
//    private void loadData() {
//
//    }

}