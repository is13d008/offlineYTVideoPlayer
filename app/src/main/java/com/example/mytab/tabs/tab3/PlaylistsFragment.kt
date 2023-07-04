package com.example.mytab.tabs.tab3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.tabs.CreatePlaylistActivity

class PlaylistsFragment : AbstractListFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        mainView = inflater.inflate(R.layout.fragment_playlists, container, false)

        val createButton = mainView.findViewById(R.id.create_list_btn) as Button

        createButton.setOnClickListener {

            val intent = Intent(requireActivity(), CreatePlaylistActivity::class.java)
            startActivity(intent)
        }

        return mainView
    }
}