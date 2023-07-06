package com.example.mytab

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.mytab.abstracts.AbstractActivity
import com.example.mytab.adapter.FragmentAdapter
import com.example.mytab.models.PlaylistItem
import com.example.mytab.models.VideoItem
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AbstractActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadJSON = MainApplication.sharePreference!!.getString("dwn_videos", "[]")
        val playlistJSON = MainApplication.playSharePreference!!.getString("PLAYLIST_DATA", "[]")

        val downloadType = object : TypeToken<ArrayList<VideoItem>>() {}.type
        val playlistType = object : TypeToken<ArrayList<PlaylistItem>>() {}.type

        MainApplication.downloadList = Gson().fromJson(downloadJSON, downloadType)
        MainApplication.playList = Gson().fromJson(playlistJSON, playlistType)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        adapter = FragmentAdapter(supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        setupTabIcons()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null){
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) : Unit {

            }

            override fun onPageSelected(position: Int) {
//                pageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(com.example.mytab.R.drawable.baseline_search_24);
        tabLayout.getTabAt(1)?.setIcon(com.example.mytab.R.drawable.baseline_download_24);
        tabLayout.getTabAt(2)?.setIcon(com.example.mytab.R.drawable.baseline_playlist_play_24);
        tabLayout.getTabAt(3)?.setIcon(com.example.mytab.R.drawable.baseline_settings_24);
    }

    private fun getAllVideos(){

    }

}