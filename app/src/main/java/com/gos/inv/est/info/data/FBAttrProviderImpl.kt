package com.gos.inv.est.info.data

import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.gos.inv.est.info.domain.grey.FBAttrProvider
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FBAttrProviderImpl @Inject constructor() : FBAttrProvider {

    override suspend fun provideFB(context: Context, appID: String, appToken: String): String =
        suspendCoroutine { cont ->

            FacebookSdk.setApplicationId(appID)
            FacebookSdk.setAdvertiserIDCollectionEnabled(true)
            FacebookSdk.setClientToken(appToken)
            FacebookSdk.setAutoInitEnabled(true)
            FacebookSdk.fullyInitialize()
            FacebookSdk.sdkInitialize(context)

            AppLinkData.fetchDeferredAppLinkData(context) { appLinkData ->
                val fbresponse = appLinkData?.targetUri.toString()
                cont.resume(appLinkData?.targetUri.toString())
            }
        }
}