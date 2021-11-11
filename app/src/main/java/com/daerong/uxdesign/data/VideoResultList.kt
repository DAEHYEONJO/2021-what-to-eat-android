package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoResultList(
    @SerializedName("meta") var meta : SearchMeta,
    @SerializedName("documents") var videos : ArrayList<VClipResult>
):Serializable
