package com.example.mytab.abstracts

import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.SongItem
import com.example.mytab.models.VideoItem

abstract class AbstractFragment : Fragment(), YoutubeListener {

    lateinit var mainView : View

    override fun clickAtPosition(position: Int, data: VideoItem) {

    }

    override fun clickAtPlaylistPosition(position: Int, data: SongItem) {
    }
}