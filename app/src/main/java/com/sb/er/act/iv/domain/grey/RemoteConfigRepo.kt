package com.sb.er.act.iv.domain.grey

import android.content.Context

interface RemoteConfigRepo {
    suspend fun takeDataFromADistantStorage(context: Context): Map<String, String>
}