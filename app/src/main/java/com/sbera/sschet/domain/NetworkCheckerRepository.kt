package com.sbera.sschet.domain

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}