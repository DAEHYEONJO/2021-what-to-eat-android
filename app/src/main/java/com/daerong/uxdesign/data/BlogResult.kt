package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class BlogResult(
    @SerializedName("title") var title : String,
    @SerializedName("contents") var contents : String,
    @SerializedName("url") var url : String,
    @SerializedName("blogname") var blogResult: String,
    @SerializedName("thumbnail") var thumbnail : String,
    @SerializedName("datetime") var datetime : Date
):Serializable
