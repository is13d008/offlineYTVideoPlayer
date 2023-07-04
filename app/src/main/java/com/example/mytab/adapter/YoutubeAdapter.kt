package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.SearchData
import com.example.mytab.models.VideoItem
import com.example.mytab.tabs.YoutubeListActivity
import com.squareup.picasso.Picasso

class YoutubeAdapter(var ytList: List<VideoItem>, val listener: YoutubeListener) : RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder<VideoItem>>() {

    var onItemClick : ((VideoItem) -> Unit)? = null
    lateinit var searchN : List<SearchData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder<VideoItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return YoutubeViewHolder(view)
    }

    class YoutubeViewHolder<VideoItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

    override fun onBindViewHolder( holder: YoutubeViewHolder<VideoItem>, position: Int ) {

        Picasso.get().load(ytList.get(position).snippet!!.thumbnails!!.medium!!.url!!).into(holder.imageItem)
        holder.textItem.setText(ytList.get(position).snippet!!.title!!)
        holder.descItem.setText(ytList.get(position).snippet!!.description!!)
        holder.linkItem.setText(ytList.get(position).id!!.videoId!!)
        holder.dateItem.setText(ytList.get(position).snippet!!.publishedAt!!)

        holder.itemView.setOnClickListener {
            listener.clickAtPosition(position, ytList[position])
//            println("SetOnClickListener deerh damjij bui utguud" + ytList[position] + position)
        }
    }

    override fun getItemCount(): Int {
        return ytList.size
    }

}