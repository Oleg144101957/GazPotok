package com.sb.er.act.iv.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}