package com.example.mytab.tabs.tab1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication.Companion.SINGER_NAME
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.SearchAdapter
import com.example.mytab.interfaces.SearchListener
import com.example.mytab.models.SearchData
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import com.example.mytab.tabs.YoutubeListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : AbstractListFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private var snList = ArrayList<SearchData>()
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = mainView.findViewById(R.id.recyclerNameView)
        searchView = mainView.findViewById(R.id.search_name)

        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

//        addDataToList()

        adapter = SearchAdapter(snList , object : SearchListener{
            override fun clickAtPosition(position: Int, data: SearchData) {

                val intent = Intent(requireActivity(), YoutubeListActivity::class.java)
                intent.putExtra(SINGER_NAME, data)
                startActivity(intent)

            }

            override fun suggestCompletion(data: SearchData) {
                TODO("Not yet implemented")
            }

        })
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
                        println("MESSAGE : " + t.localizedMessage)
                    }
                })
                return true
            }
            
        })

        return mainView
    }

}