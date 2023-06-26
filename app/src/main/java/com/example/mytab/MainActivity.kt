package com.example.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.mytab.adapter.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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