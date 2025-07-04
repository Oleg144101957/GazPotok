package com.gasin.est.vkl.domain.grey

import android.content.Context

interface FBAttrProvider {

    suspend fun provideFB(context: Context, appID: String, appToken: String): String
}