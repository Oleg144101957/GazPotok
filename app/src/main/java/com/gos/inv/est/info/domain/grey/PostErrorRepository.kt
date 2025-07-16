package com.gos.inv.est.info.domain.grey

import com.gos.inv.est.info.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}