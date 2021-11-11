package com.daerong.uxdesign.application

import android.app.Application
import android.content.Context

class GlobalApplication:Application() {
    init {
        instance = this
    }

    companion object{
        private var instance : GlobalApplication? = null
        fun getContext():Context{
            return instance!!.applicationContext
        }
    }

}