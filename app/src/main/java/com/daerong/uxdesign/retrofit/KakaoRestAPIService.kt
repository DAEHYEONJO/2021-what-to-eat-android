package com.daerong.uxdesign.retrofit

import android.content.Context
import android.media.Image
import android.util.Log
import com.daerong.uxdesign.R
import com.daerong.uxdesign.application.GlobalApplication
import com.daerong.uxdesign.data.*
import retrofit2.await
import java.net.URL
import kotlin.random.Random

class KakaoRestAPIService{
    companion object{
        val authorization = "KakaoAK ${GlobalApplication.getContext().getString(R.string.kakao_rest_api_key)}"
    }
    suspend fun enqueCategoryPlace(x : String, y : String):PlaceList?{
        Log.d("kakaoapiservice","enquecategoryplace")
        return KakaoRestAPIClient.getInstance()?.getCategoryPlace(
            key = authorization,categoryGroupCode = "FD6",x = x, y  = y,radius = 10000,sort="distance"
        )?.await()
    }

    suspend fun enqueKeywordPlace(x:String, y:String):PlaceList?{
        return KakaoRestAPIClient.getInstance()?.getKeywordPlaces(
            key = authorization, query = "점심", x = x, y = y, sort = "distance",radius = 10000, page = 1
        )?.await()
    }

    suspend fun enquePlaceImage(query: String):ImageResultList?{
        Log.d("kakaoapiservice","enquePlaceImage")
        return KakaoRestAPIClient.getInstance()?.getPlaceImage(
            key = authorization, query = query, sort = "accuracy"
        )?.await()
    }

    suspend fun getPlaceWithImage(x : String, y : String):PlaceWithImage{
        val placeList = enqueKeywordPlace(x,y)
        val size = placeList?.places?.size
        val index = Random.nextInt(size!!)
        val selectedPlace = placeList.places[index]
        var imageResultList = enquePlaceImage(query = "${selectedPlace.addressName.split(" ")[1]}${selectedPlace.placeName}")
        Log.d("getPlaceWithImage","${selectedPlace.addressName}//${selectedPlace.addressName.split(" ")[1]}${selectedPlace.placeName} count : ${imageResultList?.meta?.totalCount}")
        if (imageResultList?.meta?.totalCount == 0){
           imageResultList = enquePlaceImage(query = selectedPlace.placeName)
        }
        val imageUrl = imageResultList!!.documents[0].imageUrl
        return PlaceWithImage(selectedPlace, imageUrl)
    }

    suspend fun enqueVideoList(query:String): ArrayList<VClipResult> {
        Log.d("enqueVideoListQuery",query.substring(1))
        val vClipResult = ArrayList<VClipResult>()
        KakaoRestAPIClient.getInstance()?.getVideoList(key = authorization, query = "${query.substring(1)}음식레시피", sort = "accuracy")
            ?.await()?.videos?.forEach {
                val url = URL(it.url)
                if (url.host=="www.youtube.com"){
                    val id = url.query.substringAfter('=')
                    val thumbNailUrl = "https://img.youtube.com/vi/${id}/hqdefault.jpg"
                    it.id = id
                    it.thumbNailUrl = thumbNailUrl
                    vClipResult.add(it)
                    Log.d("kakaoapiservice","id : ${it.title} url: ${it.url}")
                }
            }
        Log.d("kakaoapiservice","return")
        vClipResult.forEach {
            Log.d("kakaoapiservice","vclip ~ id : ${it.title} url: ${it.url}")
        }
        return vClipResult
   }

    suspend fun enqueBlogList(query: String): ArrayList<BlogResult>? {
        return KakaoRestAPIClient.getInstance()?.getBlogList(key= authorization, query = query, sort = "recency", size = 50)
            ?.await()?.blogResults
    }
}