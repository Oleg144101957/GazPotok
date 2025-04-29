package com.trao.dev.tech.domain

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}