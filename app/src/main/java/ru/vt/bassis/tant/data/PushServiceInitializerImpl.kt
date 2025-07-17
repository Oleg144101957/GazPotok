package ru.vt.bassis.tant.data

import android.content.Context
import com.onesignal.OneSignal
import ru.vt.bassis.tant.domain.grey.PushServiceInitializer
import javax.inject.Inject

class PushServiceInitializerImpl @Inject constructor() :
    PushServiceInitializer {

    override fun initializePushService(advertID: String, context: Context) {
        OneSignal.initWithContext(context, ONE_SIGNAL_DEV_KEY)
        OneSignal.login(advertID)
        OneSignal.User.pushSubscription.optIn()
    }

    companion object {
        private const val ONE_SIGNAL_DEV_KEY = "fcbcb8eb-f5f5-4944-8f7c-b8b4964b51f7"
    }
}