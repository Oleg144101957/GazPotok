package gf.na.chisle.nia.domain.grey

interface NetworkCheckerRepository {
    fun isConnectionAvailable(): Boolean
}