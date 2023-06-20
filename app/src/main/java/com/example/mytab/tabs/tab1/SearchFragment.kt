package com.example.mytab.tabs.tab1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.SearchAdapter
import com.example.mytab.databinding.ActivityMainBinding
import com.example.mytab.models.ApiResponse
import com.example.mytab.models.SearchData
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class SearchFragment : AbstractListFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private var snList = ArrayList<SearchData>()
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = mainView.findViewById(R.id.recyclerNameView)
        searchView = mainView.findViewById(R.id.search_name)

        Log.d(TAG, "onCreateView: started");

        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

//        addDataToList()

        adapter = SearchAdapter(snList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                filterList(newText)
                service.getAllSuggests("firefox", "yt", newText!!).enqueue(object : Callback<Any> {

                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        println("Youtube Search 1")
                        println(response.body()!!::class.simpleName)
                        val array = response.body()!! as ArrayList<List<String>>
                        println("RESULT : " + array.get(1))
                        snList.clear()
                        for (suggest in array.get(1)) {
                            snList.add(SearchData(suggest))
                        }
                        adapter.notifyDataSetChanged()

                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        println("Youtube Search 2 Failure")
                        println("MESSAGE : " + t.localizedMessage)
                    }
                })
                return true
            }
            
        })

//        service.getAllVideos("AIzaSyBji_w43hXD4qOPYxxP18IWa-DzdMHbuDk", "joji", "video", "snippet").enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                println("XOXO 1")
//                println(response.body())
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                println("XOXO 2")
//
//            }
//        })

        return mainView
    }


}