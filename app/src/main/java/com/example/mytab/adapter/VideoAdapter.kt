package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.models.VideoItem

class VideoAdapter(private val videoList: ArrayList<VideoItem>) : RecyclerView.Adapter<VideoAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return ModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}