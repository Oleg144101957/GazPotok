package ru.gz.po.tok41.domain.grey

interface GaidRepository {

    suspend fun getGaid():String

}