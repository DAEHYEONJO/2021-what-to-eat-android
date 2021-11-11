package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BlogList(
    @SerializedName("meta") var meta : SearchMeta,
    @SerializedName("documents") var blogResults : ArrayList<BlogResult>
):Serializable
