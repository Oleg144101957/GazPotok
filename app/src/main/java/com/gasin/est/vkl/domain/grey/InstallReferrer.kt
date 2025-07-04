package com.gasin.est.vkl.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
