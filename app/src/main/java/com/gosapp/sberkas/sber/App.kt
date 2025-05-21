package com.gosapp.sberkas.sber

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
        OneSignal.setAppId("98647c3b-0e1d-4811-8ddc-ebc9c4cfea21")
    }
}