package com.gosapp.sberkas.sber.domain

interface GaidRepository {

    suspend fun getGaid():String

}