package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.models.ApiResponse
import org.w3c.dom.Text

class YoutubeAdapter(var ytList: List<ApiResponse>) :
    RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return ytList.size
    }


    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.song_image)
        val textView : TextView = itemView.findViewById(R.id.singer)
        val descView : TextView = itemView.findViewById(R.id.song_description)
        val linkView : TextView = itemView.findViewById(R.id.video_source)
        val dateView : TextView = itemView.findViewById(R.id.video_date)
    }


}