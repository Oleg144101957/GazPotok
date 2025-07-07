package com.qua.ntum.barh.at.data

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.qua.ntum.barh.at.domain.grey.GaidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GaidRepositoryImpl  @Inject constructor(
    private val context: Context
) : GaidRepository {

    override suspend fun getGaid(): String {
        return withContext(Dispatchers.IO) {
            try {
                val advertisingIdInfo =
                    AdvertisingIdClient.getAdvertisingIdInfo(context)
                val brinId = advertisingIdInfo.id
                OneSignal.setExternalUserId(brinId.toString())
                brinId.toString()
            } catch (e: Exception) {
                ERROR
            }
        }
    }

    companion object {
        private const val ERROR = "AD_ID ERROR"
    }
}