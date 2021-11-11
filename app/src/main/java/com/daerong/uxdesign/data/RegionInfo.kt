package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegionInfo(
    @SerializedName("region") var region: ArrayList<String>,
    @SerializedName("keyword") var keyword:String,
    @SerializedName("selected_region") var selectedRegion:String
):Serializable
