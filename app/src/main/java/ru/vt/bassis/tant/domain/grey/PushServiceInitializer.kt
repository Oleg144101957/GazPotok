package ru.vt.bassis.tant.domain.grey

import android.content.Context

interface PushServiceInitializer {
    fun initializePushService(advertID: String, context: Context)
}