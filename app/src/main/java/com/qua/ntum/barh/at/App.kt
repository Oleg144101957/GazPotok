package com.qua.ntum.barh.at

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
        OneSignal.setAppId("83fabe1e-b948-4a05-a347-8afdf8c0bfd7")
    }
}