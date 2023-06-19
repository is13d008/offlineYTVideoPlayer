package com.example.mytab.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ThumbnailsYt {
    @SerializedName("default") var default : Default? = null
    @SerializedName("medium") var medium : Medium? = null
    @SerializedName("high") var high : High? = null
}

data class Default(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
)

data class Medium(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
)

data class High(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
)