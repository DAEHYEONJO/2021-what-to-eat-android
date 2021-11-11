package com.daerong.uxdesign.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daerong.uxdesign.MainActivity
import com.daerong.uxdesign.R
import com.daerong.uxdesign.fragments.HealthFragment
import com.daerong.uxdesign.fragments.LunchFragment
import com.daerong.uxdesign.fragments.RecipeFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}