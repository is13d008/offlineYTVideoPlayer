package com.example.mytab.interfaces

import com.example.mytab.models.SearchData

interface SearchListener {

    fun clickAtPosition(position : Int , data : SearchData)
    fun suggestCompletion(data: SearchData)

}