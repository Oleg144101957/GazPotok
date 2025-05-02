package com.sbera.sschet.domain

interface GaidRepository {

    suspend fun getGaid():String

}