package com.trao.dev.tech.domain

import android.content.Context

interface FBAttrProvider {

    suspend fun provideFB(context: Context, appID: String, appToken: String): String
}