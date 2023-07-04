package com.example.mytab.tabs.tab2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import com.example.mytab.tabs.PlayerActivity


class DownloadFragment : AbstractListFragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: YoutubeAdapter


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_download, container, false)

        recyclerView = mainView.findViewById(R.id.download_recycler_view)

        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        adapter = YoutubeAdapter(MainApplication.Companion.downloadList, this)

//        adapter = YoutubeAdapter(MainApplication.downloadList, object : YoutubeListener {
//            override fun clickAtPosition(position: Int, data: VideoItem) {
//
//                val intent = Intent(requireContext(), PlayerActivity::class.java)
//                val bundle = Bundle()
//                bundle.putSerializable(MainApplication.VIDEO_DATA,data)
//                bundle.putString("KEY","qwerty")
//                intent.putExtras(bundle)
//                startActivity(intent)
//
//            }
//        })

        recyclerView. adapter = adapter

        val sharePreferences = activity?.getSharedPreferences("MY_LIST_DATA", Context.MODE_PRIVATE)

        return mainView
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

        override fun clickAtPosition(position: Int, data: VideoItem) {
            super.clickAtPosition(position, data)

            val intent = Intent(requireContext(), PlayerActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(MainApplication.VIDEO_DATA,data)
            bundle.putString("KEY","qwerty")
            intent.putExtras(bundle)
            startActivity(intent)

        }


}