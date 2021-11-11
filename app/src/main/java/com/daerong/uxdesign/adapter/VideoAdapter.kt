package com.daerong.uxdesign.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.manager.TargetTracker
import com.bumptech.glide.request.RequestOptions
import com.daerong.uxdesign.YouTubeActivity
import com.daerong.uxdesign.application.GlobalApplication
import com.daerong.uxdesign.data.VClipResult
import com.daerong.uxdesign.databinding.VideoRowBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.net.URL

class VideoAdapter (var videoList:ArrayList<VClipResult>):RecyclerView.Adapter<VideoAdapter.ViewHolder>(){
    inner class ViewHolder(val binding : VideoRowBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.youtubePlayView.addFullScreenListener(
                object : YouTubePlayerFullScreenListener{
                    override fun onYouTubePlayerEnterFullScreen() {

                        Log.d("videoAdapter","${videoList[adapterPosition].title} 풀스크린 엔터 !!")
                        val intent = Intent(binding.root.context, YouTubeActivity::class.java)
                        intent.putExtra("videoId",videoList[adapterPosition].id)
                        binding.root.context.startActivity(intent)
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        Log.d("videoAdapter","${videoList[adapterPosition].title} 풀스크린 엑싯 !!")

                    }
                }
            )
        }
        fun bind(data : VClipResult, position : Int){
            binding.vClipResult = data
            binding.executePendingBindings()

            /*val youTubePlayerListener = object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    Log.d("videoAdapter","title : ${data.title} id ${data.id} adapterpos : $adapterPosition position : $position")
                    youTubePlayer.loadVideo( data.id, 0F)
                    binding.youtubePlayView.addFullScreenListener(object:YouTubePlayerFullScreenListener{
                        override fun onYouTubePlayerEnterFullScreen() {
                            Log.d("videoAdapter","enter full screen")
                            val intent = Intent(binding.root.context, YouTubeActivity::class.java)
                            intent.putExtra("videoId",data.id)
                            binding.root.context.startActivity(intent)
                        }

                        override fun onYouTubePlayerExitFullScreen() {
                            Log.d("videoAdapter","exit full screen")
                        }
                    })
                }
            }*/
            binding.youtubePlayView.run {
                this.getYouTubePlayerWhenReady(object : YouTubePlayerCallback{
                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer){
                        youTubePlayer.loadVideo(data.id,0f)
                        Log.d("videoAdapter","${data.title} onYouTubePlayer")
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VideoRowBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("videoAdapter","position : ${position} ${videoList[position].id}")
        holder.bind(videoList[position], position )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    companion object{
        /*@JvmStatic
        @BindingAdapter("setImage")
        fun setImage(thumbnail : ImageView, url : String?){
            if (url!=null){
                Log.d("videoAdapter","setImage : ${url}")
                Glide.with(thumbnail.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.bitmapTransform(CenterCrop()))
                    .into(thumbnail)
            }
        }*/

        /*@JvmStatic
        @BindingAdapter("set_video_id")
        fun setVideoId(youTubePlayerView: YouTubePlayerView, videoId : String){
            youTubePlayerView.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }
            )
        }*/

    }
}