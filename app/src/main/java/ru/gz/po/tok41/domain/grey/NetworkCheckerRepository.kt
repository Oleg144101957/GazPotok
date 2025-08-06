package ru.gz.po.tok41.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}