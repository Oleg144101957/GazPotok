package com.trao.dev.tech.domain

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
