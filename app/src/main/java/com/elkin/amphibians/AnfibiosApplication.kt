package com.elkin.amphibians

import android.app.Application
import com.elkin.amphibians.data.AppContainer
import com.elkin.amphibians.data.DefaultAppContainer

class AnfibiosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}