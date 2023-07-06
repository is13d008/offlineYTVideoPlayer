package com.example.mytab.tabs.tab3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytab.MainApplication
import com.example.mytab.MainApplication.Companion.LIST_TYPE_PLAYLIST
import com.example.mytab.MainApplication.Companion.PLAYLIST_DATA
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment
import com.example.mytab.adapter.PlaylistAdapter
import com.example.mytab.models.PlaylistItem
import com.example.mytab.models.VideoItem
import com.example.mytab.retrofit
import com.example.mytab.services.ServiceInterface
import com.example.mytab.tabs.YoutubeListActivity
import java.util.Date
import java.text.SimpleDateFormat
import com.google.gson.Gson

class PlaylistsFragment : AbstractListFragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PlaylistAdapter
    var gson = Gson()
//    lateinit var editText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        mainView = inflater.inflate(R.layout.fragment_playlists, container, false)

        recyclerView = mainView.findViewById(R.id.playlist_recyclerview)

        val createButton = mainView.findViewById(R.id.create_list_btn) as Button


        val service = retrofit.create(ServiceInterface::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        adapter = PlaylistAdapter(MainApplication.playList, this)
        recyclerView.adapter = adapter

        val sharePreferences = activity?.getSharedPreferences("MY_PLAYLISTS", Context.MODE_PRIVATE)

        createButton.setOnClickListener {


            val builder = AlertDialog.Builder(requireActivity())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.playlist_title)

                with(builder) {
                    setTitle("New playlist title")
                    setPositiveButton("Create"){ dialog, which ->
                        val title = editText.text.toString()

                        val currentDate = Date()
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                        val formattedDate = dateFormat.format(currentDate)
                        val newPlaylistObj = PlaylistItem(title, formattedDate, ArrayList<VideoItem>())

                        MainApplication.playList.add(newPlaylistObj)

                        var jsonString = gson.toJson(MainApplication.playList)
                        val editorPlay = MainApplication.playSharePreference?.edit()
                        editorPlay?.putString("PLAYLIST_DATA", jsonString)
                        editorPlay?.commit()

                        adapter.notifyDataSetChanged()
//                        println("Createdd")
                    }

                    setNegativeButton("Cancel"){ dialog, which ->
                        Log.d("Main", "Negative button clicked")
                    }
                    setView(dialogLayout)
                    show()
                }
        }

        return mainView
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
//        print("PlayLitFragmentt")
    }

    override fun clickAtPosition(position: Int, data: Any) {
        super.clickAtPosition(position, data)

        val intent = Intent(requireActivity(), YoutubeListActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(PLAYLIST_DATA, data as PlaylistItem)
        intent.putExtra("LIST_TYPE",MainApplication.LIST_TYPE_PLAYLIST)
        intent.putExtras(bundle)
        startActivity(intent)

    }

}