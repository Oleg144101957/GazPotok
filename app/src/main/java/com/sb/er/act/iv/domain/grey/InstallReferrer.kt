package com.gosapp.sberkas.sber.domain

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
