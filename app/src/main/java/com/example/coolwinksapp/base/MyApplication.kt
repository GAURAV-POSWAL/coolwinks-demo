package com.example.coolwinksapp.base

import android.app.Application
import com.example.coolwinksapp.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent = DaggerAppComponent.create()

}