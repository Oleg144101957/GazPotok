package ru.tan.alitic.rch.domain.grey

import android.content.Context

interface LoadingRepository {

    suspend fun loadUrl(token: String, context: Context): String

}