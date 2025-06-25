package com.example.androidlistadapterexampledemo.module

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationClass
        fun get(): ApplicationClass = instance
    }
}