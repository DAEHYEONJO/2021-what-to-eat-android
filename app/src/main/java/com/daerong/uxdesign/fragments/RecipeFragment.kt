package com.daerong.uxdesign.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.daerong.uxdesign.R
import com.daerong.uxdesign.databinding.FragmentRecipeBinding
import com.daerong.uxdesign.viewmodel.MyViewModel
import com.magicgoop.tagsphere.OnTagTapListener
import com.magicgoop.tagsphere.item.TagItem
import com.magicgoop.tagsphere.item.TextTagItem
import java.util.*


class RecipeFragment : Fragment() {

    companion object{
        fun getInstance() = RecipeFragment()
    }

    var binding : FragmentRecipeBinding? = null
    private val myViewModel : MyViewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("fragmentstate","RecipeFragment onCreateView")
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.run {
            viewModel = myViewModel
            lifecycleOwner = requireActivity()
        }
        myViewModel.recipeFragment = this
        setTagSphereView()
        /*if (myViewModel.bottomSheetIsShowing.value!!){
            Log.d("fragmentstate","bottomSheetIsShowing True!!")
            myViewModel.showBottomSheetFragment()
        }else{
            Log.d("fragmentstate","bottomSheetIsShowing False!!")
        }*/
    }

    private fun setTagSphereView(){
        binding!!.tagSphereView.run{
            setTextPaint(
                TextPaint().apply {
                    isAntiAlias = true
                    textSize = 80F
                    color = Color.BLACK
                }
            )
            val strArray = resources.getStringArray(R.array.recipe_keyword_array)
            (strArray.indices).map {
                TextTagItem(text = strArray[it])
            }.toList().let {
                addTagList(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("fragmentstate","RecipeFragment Destroy")
        binding = null
    }
}