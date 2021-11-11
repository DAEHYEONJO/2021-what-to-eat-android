package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceMeta(
    @SerializedName("total_count") var totalCount : Int,
    @SerializedName("pageable_count") var pageableCount: Int,
    @SerializedName("is_end") var isEnd : Boolean,
    @SerializedName("same_name") var sameName:RegionInfo
):Serializable
