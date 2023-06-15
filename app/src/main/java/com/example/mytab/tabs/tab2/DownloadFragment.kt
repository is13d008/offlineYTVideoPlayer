package com.example.mytab.tabs.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytab.R
import com.example.mytab.abstracts.AbstractListFragment

class DownloadFragment : AbstractListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_download, container, false)
        return mainView
    }
}