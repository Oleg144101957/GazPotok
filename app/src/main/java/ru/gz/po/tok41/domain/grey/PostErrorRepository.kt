package ru.gz.po.tok41.domain.grey

import ru.gz.po.tok41.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}