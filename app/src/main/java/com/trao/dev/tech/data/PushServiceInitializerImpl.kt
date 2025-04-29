package com.trao.dev.tech.data

import android.content.Context
import com.onesignal.OneSignal
import com.trao.dev.tech.domain.PushServiceInitializer
import javax.inject.Inject

class PushServiceInitializerImpl @Inject constructor() :
    PushServiceInitializer {

    override fun initializePushService(advertID: String,context: Context) {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ONE_SIGNAL_DEV_KEY)
        OneSignal.setExternalUserId(advertID)
        OneSignal.promptForPushNotifications(true)
    }

    companion object {
        private const val ONE_SIGNAL_DEV_KEY = "7d4faaeb-4e8e-4679-8596-ed0b3d8c6e96"
    }
}