package ru.vt.bassis.tant.iv.domain

import ru.vt.bassis.tant.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}