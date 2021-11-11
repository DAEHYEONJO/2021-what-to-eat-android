package com.daerong.uxdesign.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.baoyz.widget.RefreshDrawable
import com.daerong.uxdesign.R
import com.daerong.uxdesign.databinding.FragmentLunchBinding
import com.daerong.uxdesign.viewmodel.MyViewModel


class LunchFragment : Fragment() {

    companion object{
        fun getInstance() = LunchFragment()
    }

    private var binding : FragmentLunchBinding? = null
    private val myViewModel : MyViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("fragmentstate","LunchFragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lunch, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBindingProperties()
    }

    private fun setBindingProperties(){
        binding!!.run {
            viewModel = myViewModel
            lifecycleOwner = requireActivity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.swipeLayout?.removeAllViewsInLayout()
        binding = null
        Log.d("fragmentstate","LunchFragment Destroy")
    }

}