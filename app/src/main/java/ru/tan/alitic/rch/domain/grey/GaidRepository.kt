package ru.tan.alitic.rch.domain.grey

interface GaidRepository {

    suspend fun getGaid():String

}