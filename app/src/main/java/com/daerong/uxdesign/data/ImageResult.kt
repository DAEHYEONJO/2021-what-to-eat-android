package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ImageResult(
    @SerializedName("collection") var collection : String,
    @SerializedName("thumbnail_url") var thumbnailUrl : String,
    @SerializedName("image_url") var imageUrl:String,
    @SerializedName("width") var width:Int,
    @SerializedName("height") var height:Int,
    @SerializedName("display_sitename") var displaySiteName:String,
    @SerializedName("doc_url") var docUrl : String,
    @SerializedName("datetime") var dateTime : Date
):Serializable
