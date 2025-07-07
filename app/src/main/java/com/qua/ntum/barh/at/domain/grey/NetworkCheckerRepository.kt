package com.qua.ntum.barh.at.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}