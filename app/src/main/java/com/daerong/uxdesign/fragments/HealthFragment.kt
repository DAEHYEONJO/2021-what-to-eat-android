package com.daerong.uxdesign.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daerong.uxdesign.R


class HealthFragment : Fragment() {
    companion object{
        fun getInstance() = HealthFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("fragmentstate","HealthFragment Destroy")
    }
}