package com.trao.dev.tech.domain

import android.content.Context

interface PushServiceInitializer {

    fun initializePushService(advertID: String, context: Context)

}