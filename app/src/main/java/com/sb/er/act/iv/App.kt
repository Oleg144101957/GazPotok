package com.sb.er.act.iv

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
        OneSignal.setAppId("fe53c298-0544-4809-8545-f38282a0553b")
    }
}