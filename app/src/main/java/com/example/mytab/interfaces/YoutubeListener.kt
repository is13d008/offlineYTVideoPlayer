package com.example.mytab.interfaces

import com.example.mytab.models.SearchData
import com.example.mytab.models.SongItem
import com.example.mytab.models.VideoItem

interface YoutubeListener {

    fun clickAtPosition(position: Int, data: VideoItem)

    fun clickAtPlaylistPosition(position: Int, data: SongItem)
}