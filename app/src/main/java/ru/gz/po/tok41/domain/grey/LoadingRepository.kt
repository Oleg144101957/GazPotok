package ru.gz.po.tok41.domain.grey

import android.content.Context

interface LoadingRepository {

    suspend fun loadUrl(token: String, context: Context): String

}