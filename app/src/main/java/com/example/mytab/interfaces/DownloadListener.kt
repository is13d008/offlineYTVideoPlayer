package com.example.mytab.interfaces

import com.example.mytab.models.VideoItem

interface DownloadListener {
    fun savedVideos(position: Int, data: VideoItem)
}