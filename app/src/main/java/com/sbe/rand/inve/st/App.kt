package com.sbe.rand.inve.st

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
        OneSignal.setAppId("ed09768b-88e4-4fcb-9a38-23d34dd5e629")
    }
}