package com.gasin.est.vkl.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}