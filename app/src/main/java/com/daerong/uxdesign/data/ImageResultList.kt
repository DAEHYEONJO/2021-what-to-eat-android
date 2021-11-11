package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName

data class ImageResultList(
    @SerializedName("meta") var meta: SearchMeta,
    @SerializedName("documents") var documents:ArrayList<ImageResult>
)
