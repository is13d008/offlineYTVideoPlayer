package com.example.mytab.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ApiResponse {
        @SerializedName("kind") var kind : String? = null
        @SerializedName("etag") var etag : String? = null
        @SerializedName("nextPageToken") var nextPageToken : String? =null
        @SerializedName("regionCode") var regionCode : String? = null
        @SerializedName("items") var itemsArray : ArrayList<VideoItem>? = null

//    @SerializedName("items") var videoItem: List<VideoItems>

}




