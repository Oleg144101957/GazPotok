package com.gasin.est.vkl.domain.grey

interface GaidRepository {

    suspend fun getGaid():String

}