package com.qua.ntum.barh.at.domain.grey

import android.content.Context

interface PushServiceInitializer {

    fun initializePushService(advertID: String, context: Context)

}