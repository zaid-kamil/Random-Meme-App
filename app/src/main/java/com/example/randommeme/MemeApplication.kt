package com.example.randommeme

import android.app.Application
import com.example.randommeme.data.AppContainer
import com.example.randommeme.data.DefaultAppContainer

class MemeApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}