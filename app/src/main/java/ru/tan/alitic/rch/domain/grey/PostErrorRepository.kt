package ru.tan.alitic.rch.domain.grey

import ru.tan.alitic.rch.data.ErrorMessage


interface PostErrorRepository {

    suspend fun postError(message: ErrorMessage)

}