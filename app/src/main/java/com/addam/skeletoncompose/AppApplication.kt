package com.addam.skeletoncompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by addam on 24/11/2022
 */
@HiltAndroidApp
class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}