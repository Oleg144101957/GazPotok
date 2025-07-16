package com.gos.inv.est.info.domain.grey

interface GaidRepository {

    suspend fun getGaid():String

}