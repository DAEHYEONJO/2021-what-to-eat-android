package com.daerong.uxdesign.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daerong.uxdesign.R
import com.daerong.uxdesign.databinding.FragmentBottomSheetRecipeBinding
import com.daerong.uxdesign.viewmodel.MyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetRecipeFragment : BottomSheetDialogFragment() {
    var binding : FragmentBottomSheetRecipeBinding? = null
    private val myViewModel : MyViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("fragmentstate","BottomSheetRecipeFragment onCreateView")
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_bottom_sheet_recipe,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.run {
            lifecycleOwner = requireActivity()
            viewModel = myViewModel
        }
        binding!!.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
        binding!!.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("fragmentstate","BottomSheetRecipeFragment Destroy")
        myViewModel.bottomSheetIsShowing.value = false
        binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("fragmentstate","BottomSheetRecipeFragment onDestroyView*()()()()")
        dialog?.setDismissMessage(null)
    }

}