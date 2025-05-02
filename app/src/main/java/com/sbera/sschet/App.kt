package com.sbera.sschet

import android.app.Application
import com.google.firebase.FirebaseApp
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)

        OneSignal.initWithContext(this)
        OneSignal.setAppId("c4ec6faa-1156-4789-8679-c76441a64b08")
    }
}