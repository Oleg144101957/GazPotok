package com.qua.ntum.barh.at.data

import android.content.Context
import com.qua.ntum.barh.at.domain.grey.PushServiceInitializer
import com.onesignal.OneSignal
import javax.inject.Inject

class PushServiceInitializerImpl @Inject constructor() :
    PushServiceInitializer {

    override fun initializePushService(advertID: String, context: Context) {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ONE_SIGNAL_DEV_KEY)
        OneSignal.setExternalUserId(advertID)
        OneSignal.promptForPushNotifications(true)
    }

    companion object {
        private const val ONE_SIGNAL_DEV_KEY = "563d69a4-f968-4221-aedd-6c8859060a0c"
    }
}