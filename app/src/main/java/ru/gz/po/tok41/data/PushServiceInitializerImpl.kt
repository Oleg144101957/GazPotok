package ru.gz.po.tok41.data

import android.content.Context
import ru.gz.po.tok41.domain.grey.PushServiceInitializer
import com.onesignal.OneSignal
import javax.inject.Inject

class PushServiceInitializerImpl @Inject constructor() :
    PushServiceInitializer {

    override fun initializePushService(advertID: String, context: Context) {
        OneSignal.initWithContext(context, ONE_SIGNAL_DEV_KEY)
        OneSignal.login(advertID)
        OneSignal.User.pushSubscription.optIn()
    }

    companion object {
        private const val ONE_SIGNAL_DEV_KEY = "27ddcd63-826f-4a90-911c-42b04620a36b"
    }
}