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


        return mainView
    }
}