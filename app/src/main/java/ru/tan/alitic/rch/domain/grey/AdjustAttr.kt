package ru.tan.alitic.rch.domain.grey

import android.content.Context

interface AdjustAttr {

    suspend fun getAdjustAttr(token: String, context: Context): Map<String, String>

}