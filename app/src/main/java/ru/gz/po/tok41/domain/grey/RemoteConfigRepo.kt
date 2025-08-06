package ru.gz.po.tok41.domain.grey

import android.content.Context

interface RemoteConfigRepo {
    suspend fun takeDataFromADistantStorage(context: Context): Map<String, String>
}