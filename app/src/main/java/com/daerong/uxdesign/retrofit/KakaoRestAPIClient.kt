package com.daerong.uxdesign.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class KakaoRestAPIClient private constructor(){
    companion object {
        private val baseUrl = "https://dapi.kakao.com/"
        private var kakaoRestAPI : KakaoRestAPI? = null
        fun getInstance() : KakaoRestAPI?{
            if (kakaoRestAPI == null) {
                val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                kakaoRestAPI = retrofit.create(KakaoRestAPI::class.java)
            }
            return kakaoRestAPI
        }
    }

}