package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.interfaces.YoutubeListener
import com.example.mytab.models.SongItem
import com.squareup.picasso.Picasso

class PlaylistAdapter(var songList: List<SongItem>, val listener: YoutubeListener) : RecyclerView.Adapter<PlaylistAdapter.SongViewHolder<SongItem>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder<SongItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_video_item, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder<SongItem>, position: Int) {
        Picasso.get().load(songList.get(position).snippet!!.thumbnails!!.standard!!.url!!).into(holder.imageItem)
        holder.textItem.setText(songList.get(position).snippet!!.title!!)
        holder.descItem.setText(songList.get(position).snippet!!.description!!)
        holder.dateItem.setText(songList.get(position).snippet!!.publishedAt!!)

        holder.itemView.setOnClickListener {
            listener.clickAtPlaylistPosition(position, songList[position])
//            println("SetOnClickListener deerh damjij bui utguud" + ytList[position] + position)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    class SongViewHolder<SongItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageItem: ImageView
        var textItem: TextView
        var descItem: TextView
        var dateItem: TextView

        init {
            imageItem = itemView.findViewById(R.id.playl_image) as ImageView
            textItem = itemView.findViewById(R.id.playl_singer) as TextView
            descItem = itemView.findViewById(R.id.playl_description) as TextView
            dateItem = itemView.findViewById(R.id.playl_date) as TextView

        }

    }

}