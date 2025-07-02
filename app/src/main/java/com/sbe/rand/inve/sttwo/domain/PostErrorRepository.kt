package com.sbe.rand.inve.sttwo.domain

import com.sbe.rand.inve.sttwo.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}