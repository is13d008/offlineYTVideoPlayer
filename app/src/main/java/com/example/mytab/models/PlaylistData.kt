package com.example.mytab.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

class PlaylistData : Serializable {
//    @SerializedName("playlistName") var playlistName : String? = null
//    @SerializedName("playlistDate") var playlistDate : Date? = null
//    @SerializedName("songs") var songsArray : ArrayList<SongItem>? = null
    @SerializedName("kind") var kind : String? = null
    @SerializedName("etag") var etag : String? = null
    @SerializedName("nextPageToken") var nextPageToken : String? =null
    @SerializedName("pageInfo") var pageInfo : PageInfo? = null
    @SerializedName("items") var songsArray : ArrayList<SongItem>? = null
 }

data class PageInfo(
    @SerializedName("totalResults") var totalResults : Int? = null,
    @SerializedName("resultsPerPage") var resultsPerPage : Int? = null,
) : Serializable

data class SongItem(
    @SerializedName("kind") var kind : String? = null,
    @SerializedName("etag") var etag : String? = null,
    @SerializedName("id") var id : String? = null,
    @SerializedName("snippet") var snippet: Snipped? = null,
    @SerializedName("contentDetails") var contentDetails: ContentDetails? = null
) : Serializable

data class ContentDetails(
    @SerializedName("itemCount") var itemCount : Int? = null
) : Serializable

data class Snipped (
    @SerializedName("publishedAt") var publishedAt : String? = null,
    @SerializedName("channelId") var channelId : String? = null,
    @SerializedName("title") var title : String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("thumbnails") var thumbnails : ThumbnailsYoutube? = null,
    @SerializedName("channelTitle") var channelTitle : String? = null,
    @SerializedName("localized") var localized : Localized? = null,
    @SerializedName("publishTime") var publishTime : String? = null
) : Serializable

data class Localized(
    @SerializedName("title") var title : String? = null,
    @SerializedName("description") var description : String? = null,
) : Serializable

data class ThumbnailsYoutube (
    @SerializedName("default") var default : Defaults? = null,
    @SerializedName("standard") var standard : Standard? = null,
    @SerializedName("high") var high : Highs? = null
) : Serializable

data class Defaults(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
) : Serializable

data class Standard(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
) : Serializable

data class Highs(
    @SerializedName("url") var url : String? = null,
    @SerializedName("width") var width : Int? = 0,
    @SerializedName("height") var height : Int? = 0,
) : Serializable


