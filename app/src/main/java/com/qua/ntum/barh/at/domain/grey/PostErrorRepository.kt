package com.qua.ntum.barh.at.domain.grey

import com.qua.ntum.barh.at.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}