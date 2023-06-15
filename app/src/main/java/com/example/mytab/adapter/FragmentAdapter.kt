package com.example.mytab.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mytab.tabs.tab1.SearchFragment
import com.example.mytab.tabs.tab2.DownloadFragment
import com.example.mytab.tabs.tab3.PlaylistsFragment
import com.example.mytab.tabs.tab4.SettingsFragment

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SearchFragment()
            1 -> DownloadFragment()
            2 -> PlaylistsFragment()
            else -> SettingsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "Search"
            }
            1 -> {
                return "Download"
            }
            2 -> {
                return "Playlists"
            }
            3 -> {
                return "Settings"
            }
        }
        return super.getPageTitle(position)
    }



}