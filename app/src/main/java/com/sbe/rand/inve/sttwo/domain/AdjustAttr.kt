package com.gosapp.sberkas.sber.domain

import android.content.Context

interface AdjustAttr {

    suspend fun getAdjustAttr(token: String, context: Context): Map<String, String>

}