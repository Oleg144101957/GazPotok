package com.sbera.sschet.domain

import android.content.Context

interface LoadingRepository {

    suspend fun loadUrl(token: String, context: Context): String

}