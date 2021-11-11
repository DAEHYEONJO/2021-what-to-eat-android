package com.daerong.uxdesign.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Looper
import android.text.TextPaint
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.baoyz.widget.PullRefreshLayout
import com.baoyz.widget.RefreshDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.daerong.uxdesign.R
import com.daerong.uxdesign.adapter.VideoAdapter
import com.daerong.uxdesign.application.GlobalApplication
import com.daerong.uxdesign.data.*
import com.daerong.uxdesign.fragments.BottomSheetRecipeFragment
import com.daerong.uxdesign.fragments.RecipeFragment
import com.daerong.uxdesign.retrofit.KakaoRestAPIService
import com.google.android.gms.location.*
import com.magicgoop.tagsphere.OnTagTapListener
import com.magicgoop.tagsphere.TagSphereView
import com.magicgoop.tagsphere.item.TagItem
import com.magicgoop.tagsphere.item.TextTagItem
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Nullable
import java.net.URL
import kotlin.random.Random

class MyViewModel:ViewModel() {
    var curX : String? = null
    var curY : String? = null
    var placeUrl = MutableLiveData<String>()
    var placeName = MutableLiveData<String>()
    var placePhone = MutableLiveData<String>()
    var placeAddress = MutableLiveData<String>()
    var videoList = MutableLiveData<ArrayList<VClipResult>>()
    var blogList = MutableLiveData<ArrayList<BlogResult>>()
    var fusedLocationProviderClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(GlobalApplication.getContext())
    var isLoading = MutableLiveData<Boolean>(false)
    var recipeFragment : RecipeFragment? = null
    lateinit var videoAdapter : VideoAdapter
    val bottomSheet = BottomSheetRecipeFragment()
    val tagTapListener = object : OnTagTapListener {
        override fun onTap(tagItem: TagItem) {
            viewModelScope.launch {
                videoList.value = KakaoRestAPIService().enqueVideoList((tagItem as TextTagItem).text)
                videoAdapter = VideoAdapter(videoList.value!!)
                videoAdapter.notifyDataSetChanged()
                showBottomSheetFragment()
            }
        }
    }
    var curSelectedMenuId = MutableLiveData<Int>(R.id.today_random_restaurant_btn)
    var bottomSheetIsShowing = MutableLiveData<Boolean>(false)

    fun showBottomSheetFragment(){
        Log.d("fragmentstate","showBottomSheetFragment ViewModel")
        bottomSheet.show(recipeFragment!!.childFragmentManager, bottomSheet.tag)
        bottomSheetIsShowing.value = true
    }

    fun getBlogList(){
        viewModelScope.launch {
            blogList.value = KakaoRestAPIService().enqueBlogList("눈에좋은음식").also {
                it?.forEach {
                    Log.d("bloglist",it.contents)
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun getLastLocation(){
        if (!curX.isNullOrEmpty() or !curY.isNullOrEmpty()) return

        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                if (p0 == null){
                    return
                }
                for (location in p0.locations){
                    curY = location.latitude.toString()
                    curX = location.longitude.toString()
                    return
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it!=null){
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                curY = it.latitude.toString()
                curX = it.longitude.toString()
                setPlaceWithImage(curX!!, curY!!)
                Log.d("location","$curX $curY")
            }
        }
    }

    fun setPlaceWithImage(x : String, y : String){
        viewModelScope.launch {
            isLoading.value = true
            val placeWithImage = KakaoRestAPIService().getPlaceWithImage(x,y)
            placeUrl.value = placeWithImage.imageUrl
            placeName.value = placeWithImage.place.placeName
            placePhone.value = placeWithImage.place.phone
            placeAddress.value = placeWithImage.place.addressName
            isLoading.value = false
        }
    }

    companion object{
        @JvmStatic
        @BindingAdapter("placeImage")
        fun loadImage(view : ImageView, url : String?){
            if (url!=null){
                Glide.with(view.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(view)
            }
        }
    }


}