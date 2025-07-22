package ru.tan.alitic.rch.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
