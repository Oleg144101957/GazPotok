package gf.na.chisle.nia.domain.grey

interface InstallReferrer {

    suspend fun fetchReferrer(): String?
}
