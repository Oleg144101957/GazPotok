package com.sbera.sschet.domain

import android.content.Context

interface AdjustAttr {

    suspend fun getAdjustAttr(token: String, context: Context): Map<String, String>

}