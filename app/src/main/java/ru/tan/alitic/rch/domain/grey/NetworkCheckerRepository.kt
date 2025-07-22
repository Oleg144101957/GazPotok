package ru.tan.alitic.rch.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}