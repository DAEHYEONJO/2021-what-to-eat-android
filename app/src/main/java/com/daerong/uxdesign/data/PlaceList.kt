package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceList(
    @SerializedName("meta") var placeMeta: PlaceMeta,
    @SerializedName("documents") var places : ArrayList<Place>
):Serializable
