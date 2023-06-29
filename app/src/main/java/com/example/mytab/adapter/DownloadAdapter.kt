package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.interfaces.DownloadListener
import com.example.mytab.models.VideoItem

class DownloadAdapter(var downloadList: List<VideoItem>, param: DownloadListener) : RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder<VideoItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder<VideoItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return DownloadViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder<VideoItem>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return downloadList.size
    }

    class DownloadViewHolder<VideoItem>(downloadView: View) : RecyclerView.ViewHolder(downloadView) {

        var imageItem: ImageView
        var textItem: TextView
        var descItem: TextView
        var linkItem: TextView
        var dateItem: TextView

        init {
            imageItem = itemView.findViewById(R.id.song_image) as ImageView
            textItem = itemView.findViewById(R.id.singer) as TextView
            descItem = itemView.findViewById(R.id.song_description) as TextView
            linkItem = itemView.findViewById(R.id.video_source) as TextView
            dateItem = itemView.findViewById(R.id.video_date) as TextView

        }

    }
}