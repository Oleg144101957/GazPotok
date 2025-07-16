package com.sb.er.act.iv.domain

import com.sb.er.act.iv.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}