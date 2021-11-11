package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import java.io.Serializable
import java.util.*

data class VClipResult(
    @SerializedName("title") var title : String,
    @SerializedName("url") var url : String,
    @SerializedName("datetime") var dataTime : Date,
    @SerializedName("play_time") var playTime : Int,
    @SerializedName("thumbnail") var thumbNail : String,
    @SerializedName("author") var author : String,
    var thumbNailUrl : String="",
    var id : String = ""
):Serializable
