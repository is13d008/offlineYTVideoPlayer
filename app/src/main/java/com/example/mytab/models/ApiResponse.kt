package com.example.mytab.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ApiResponse {
        @SerializedName("kind") var kind : String? = null
        @SerializedName("etag") var etag : String? = null
        @SerializedName("nextPageToken") var nextPageToken : String? =null
        @SerializedName("regionCode") var regionCode : String? = null
        @SerializedName("items") var itemsArray : ArrayList<VideoItem>? = null
}

data class VideoItem (
        @SerializedName("kind") var kind : String? = null,
        @SerializedName("etag") var etag : String? = null,
        @SerializedName("videoId") var videoId : videoId? = null,
        @SerializedName("snippet") var snippet: Snippet? = null
)

data class Snippet (
        @SerializedName("publishedAt") var publishedAt : Date? = null,
        @SerializedName("channelId") var channelId : String? = null,
        @SerializedName("title") var title : String? = null,
        @SerializedName("description") var description : String? = null,
        @SerializedName("thumbnails") var thumbnails : ThumbnailsYt? = null,
        @SerializedName("channelTitle") var channelTitle : String? = null,
        @SerializedName("liveBroadcastContent") var liveBroadcastContent : String? = null,
        @SerializedName("publishTime") var publishTime : Date? = null
)

data class videoId(
        @SerializedName("kind") var kind : String? = null,
        @SerializedName("videoId") var videoId : String? = null,
)

data class ThumbnailsYt (
        @SerializedName("default") var default : Default? = null,
        @SerializedName("medium") var medium : Medium? = null,
        @SerializedName("high") var high : High? = null
        )

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


