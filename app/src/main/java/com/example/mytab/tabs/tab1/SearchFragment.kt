package com.example.mytab.tabs.tab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.models.ApiResponse
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceBuilder
import com.example.mytab.services.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : AbstractListFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_search, container, false)

        val service = retrofit.create(ServiceInterface::class.java)
        println("XOXO")
        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", "joji", "video", "snippet").enqueue(object : Callback<ApiResponse> {
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