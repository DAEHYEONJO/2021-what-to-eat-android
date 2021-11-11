package com.daerong.uxdesign.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Place(
    @SerializedName("id") var id: String,
    @SerializedName("place_name") var placeName : String,
    @SerializedName("category_name") var categoryName:String,
    @SerializedName("category_group_code") var categoryGroupCode:String,
    @SerializedName("category_group_name") var categoryGroupName:String,
    @SerializedName("phone") var phone:String,
    @SerializedName("address_name") var addressName:String,
    @SerializedName("road_address_name") var roadAddressName:String,
    @SerializedName("x") var x : String,
    @SerializedName("y") var y : String,
    @SerializedName("place_url") var placeUrl : String,
    @SerializedName("distance") var distance : String

):Serializable