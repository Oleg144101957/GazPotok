package com.sbera.sschet.domain

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
