package com.gosapp.sberkas.sber.data

import android.content.Context
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.gosapp.sberkas.sber.domain.AdjustAttr
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AdjustAttrImpl @Inject constructor() : AdjustAttr {
    override suspend fun getAdjustAttr(token: String, context: Context): Map<String, String> =
        suspendCoroutine { cont ->
            val environment = AdjustConfig.ENVIRONMENT_PRODUCTION
            val config = AdjustConfig(context, token, environment)
            config.setLogLevel(LogLevel.VERBOSE)
            Adjust.initSdk(config)
            Adjust.getAttribution { attr ->
                cont.resume(attr.toMap())
            }
        }
}