package gf.na.chisle.nia.domain.grey

import gf.na.chisle.nia.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}