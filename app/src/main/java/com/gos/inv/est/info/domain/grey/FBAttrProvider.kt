package com.gos.inv.est.info.domain.grey

import android.content.Context

interface FBAttrProvider {

    suspend fun provideFB(context: Context, appID: String, appToken: String): String
}