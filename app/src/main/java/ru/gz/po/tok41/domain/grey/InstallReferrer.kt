package ru.gz.po.tok41.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
