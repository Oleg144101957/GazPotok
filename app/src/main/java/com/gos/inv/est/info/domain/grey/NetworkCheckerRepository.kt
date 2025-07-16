package com.gos.inv.est.info.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}