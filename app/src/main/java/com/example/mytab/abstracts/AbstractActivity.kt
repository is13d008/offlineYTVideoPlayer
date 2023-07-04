package com.example.mytab.abstracts

import androidx.appcompat.app.AppCompatActivity
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.SongItem
import com.example.mytab.models.VideoItem

open class AbstractActivity : AppCompatActivity() , YoutubeListener{
    override fun clickAtPosition(position: Int, data: VideoItem) {

    }

    override fun clickAtPlaylistPosition(position: Int, data: SongItem) {
    }


}