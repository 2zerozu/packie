package org.care.packie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PackieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
