package com.example.mytab.tabs.tab3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.YoutubeAdapter
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import com.example.mytab.tabs.CreatePlaylistActivity

class PlaylistsFragment : AbstractListFragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: YoutubeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        mainView = inflater.inflate(R.layout.fragment_playlists, container, false)

        recyclerView = mainView.findViewById(R.id.playlist_recyclerview)

        val createButton = mainView.findViewById(R.id.create_list_btn) as Button

        createButton.setOnClickListener {

            val intent = Intent(requireActivity(), CreatePlaylistActivity::class.java)
            startActivity(intent)
        }

        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        adapter = YoutubeAdapter(MainApplication.Companion.playList, this)
        recyclerView.adapter = adapter

        val sharePreferences = activity?.getSharedPreferences("MY_PLAYLISTS", Context.MODE_PRIVATE)

        return mainView
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        print("PlayLitFragmentt")
    }
}