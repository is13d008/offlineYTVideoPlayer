package com.example.mytab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.interfaces.SearchListener
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData

class SearchAdapter(var snList: List<SearchData> , val listener : SearchListener) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var onItemClick : ((ApiResponse) -> Unit)? = null

    fun setFilteredList(snList: List<SearchData>) {
        this.snList = snList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.name.text = snList[position].searchName

        holder.itemView.setOnClickListener {

            listener.clickAtPosition(position,snList[position])

        }
    }

    override fun getItemCount(): Int {
        return snList.size
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.search_result_name)
    }
}