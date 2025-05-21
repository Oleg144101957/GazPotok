package com.gosapp.sberkas.sber.domain

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}