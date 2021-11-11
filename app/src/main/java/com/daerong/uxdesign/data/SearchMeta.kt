package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchMeta(
    @SerializedName("total_count") var totalCount : Int,
    @SerializedName("pageable_count") var pageAbleCount : Int,
    @SerializedName("is_end") var inEnd : Boolean
):Serializable
