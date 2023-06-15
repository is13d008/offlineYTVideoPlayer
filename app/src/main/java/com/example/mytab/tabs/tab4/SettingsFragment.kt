package com.example.mytab.tabs.tab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment

class SettingsFragment: AbstractListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_settings, container, false)
        return mainView
    }
}