package com.gosapp.sberkas.sber.domain

import android.content.Context

interface LoadingRepository {

    suspend fun loadUrl(token: String, context: Context): String

}