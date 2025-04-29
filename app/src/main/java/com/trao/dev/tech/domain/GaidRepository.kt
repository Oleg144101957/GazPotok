package com.trao.dev.tech.domain

interface GaidRepository {

    suspend fun getGaid():String

}