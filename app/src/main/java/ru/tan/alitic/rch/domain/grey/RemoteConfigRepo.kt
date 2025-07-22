package ru.tan.alitic.rch.domain.grey

import android.content.Context

interface RemoteConfigRepo {
    suspend fun takeDataFromADistantStorage(context: Context): Map<String, String>
}