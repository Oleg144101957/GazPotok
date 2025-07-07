package com.qua.ntum.barh.at.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
