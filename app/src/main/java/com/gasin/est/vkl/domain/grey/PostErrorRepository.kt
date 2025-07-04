package com.gasin.est.vkl.domain.grey

import com.gasin.est.vkl.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}