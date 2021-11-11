package com.daerong.uxdesign.retrofit

import com.daerong.uxdesign.data.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoRestAPI {
    @GET("v2/local/search/keyword.json")
    fun getKeywordPlaces(
        @Header("Authorization") key:String,
        @Query("query") query: String,
        @Query("x") x:String,
        @Query("y") y:String,
        @Query("radius") radius: Int,
        @Query("page") page : Int,
        @Query("sort") sort:String

    ): retrofit2.Call<PlaceList>

    @GET("v2/local/search/category.json")
    fun getCategoryPlace(
        @Header("Authorization") key:String,
        @Query("category_group_code") categoryGroupCode: String,
        @Query("x") x:String,
        @Query("y") y:String,
        @Query("radius") radius : Int,
        @Query("sort") sort:String
    ): Call<PlaceList>

    @GET("v2/search/image")
    fun getPlaceImage(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("sort") sort:String
    ): Call<ImageResultList>

    @GET("/v2/search/vclip")
    fun getVideoList(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("sort") sort:String
    ): Call<VideoResultList>

    @GET("/v2/search/blog")
    fun getBlogList(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("sort") sort:String,
        @Query("size") size: Int
    ): Call<BlogList>
}