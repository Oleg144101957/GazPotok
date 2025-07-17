package ru.vt.bassis.tant.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}