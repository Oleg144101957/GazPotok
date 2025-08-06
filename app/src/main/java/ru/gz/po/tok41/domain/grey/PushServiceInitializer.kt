package ru.gz.po.tok41.domain.grey

import android.content.Context

interface PushServiceInitializer {

    fun initializePushService(advertID: String, context: Context)

}