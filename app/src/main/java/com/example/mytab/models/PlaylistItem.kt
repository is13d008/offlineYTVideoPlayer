package com.example.mytab.models

import java.io.Serializable

data class PlaylistItem(val playlistName: String, val playlistDate: String, val songs: ArrayList<VideoItem>) : Serializable{

    var isChecked : Boolean = false

}




