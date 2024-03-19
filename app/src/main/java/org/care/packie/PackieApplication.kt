package org.care.packie

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PackieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeMobileAds()
    }

    private fun initializeMobileAds() {
        MobileAds.initialize(this)
    }
}
