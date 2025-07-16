package com.gos.inv.est.info.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
