package com.daerong.uxdesign

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.daerong.uxdesign.databinding.ActivityMainBinding
import com.daerong.uxdesign.fragments.HealthFragment
import com.daerong.uxdesign.fragments.LunchFragment
import com.daerong.uxdesign.fragments.RecipeFragment
import com.daerong.uxdesign.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private var lunchFragment : LunchFragment? = null
    private var healthFragment : HealthFragment? = null
    private var recipeFragment : RecipeFragment? = null
    private var curFragment : Fragment? = null
    private var tagList = arrayOf("lunchFragment","recipeFragment","healthFragment")
    private lateinit var binding : ActivityMainBinding
    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        var flag = true
        it.forEach{
            if(it.value == false) flag = false
        }
        //if (flag) myViewModel.getLastLocation()
    }
    private val myViewModel : MyViewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationView()
        initPermissions()
        myViewModel.getLastLocation()
        viewModelValueCheck()
    }

    private fun viewModelValueCheck(){
        Log.d("fragmentstate", " curSelectedMenuId.value : ${myViewModel.curSelectedMenuId.value}")
        Log.d(
            "fragmentstate",
            " bottomSheetIsShowing.value : ${myViewModel.bottomSheetIsShowing.value}"
        )
    }


    private fun initPermissions() {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this@MainActivity, it)!= PackageManager.PERMISSION_GRANTED){
                //notGrantedPermissions.add(it)
            }
        }
        requestPermissions.launch(permissions)
    }

    private fun initBottomNavigationView() {
        when(myViewModel.curSelectedMenuId.value){

            R.id.today_random_restaurant_btn -> { beginTransaction(lunchFragment, tagList[0]) }
            R.id.today_random_recipe_video_btn -> { beginTransaction(recipeFragment, tagList[1]) }
            R.id.today_random_food_blog_btn -> { beginTransaction(healthFragment, tagList[2]) }
        }
        binding.run {
            bottomNavigationView.run {
                setOnItemSelectedListener {
                    Log.d("fragmentstate", "setOnNavigationItemSelectedListener")
                    myViewModel.curSelectedMenuId.value = it.itemId
                    var tagIndex = 0
                    when(myViewModel.curSelectedMenuId.value){
                        R.id.today_random_restaurant_btn -> {
                            curFragment = lunchFragment
                            tagIndex = 0
                        }
                        R.id.today_random_recipe_video_btn -> {
                            curFragment = recipeFragment
                            tagIndex = 1
                        }
                        R.id.today_random_food_blog_btn -> {
                            curFragment = healthFragment
                            tagIndex = 2
                        }
                    }
                    Log.d("fragmentstate","cur Frag addr : ${curFragment}")
                    beginTransaction(curFragment, tagList[tagIndex])
                    true
                }
            }
        }
    }

    private fun beginTransaction(fragment: Fragment?, tag:String){
        var newFragment = fragment
        val fr = supportFragmentManager.findFragmentByTag(tag)
        if (fr == null){
            when(tag){
                tagList[0]->{
                    newFragment = LunchFragment.getInstance()
                    lunchFragment = newFragment
                }
                tagList[1]->{
                    newFragment = RecipeFragment.getInstance()
                    recipeFragment = newFragment
                }
                tagList[2]->{
                    newFragment = HealthFragment()
                    healthFragment = newFragment
                }
            }
        }else{
            when(tag){
                tagList[0]->{newFragment = fr as LunchFragment}
                tagList[1]->{newFragment = fr as RecipeFragment}
                tagList[2]->{newFragment = fr as HealthFragment}
            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_view, newFragment!!, tag)
        }.commit()
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}