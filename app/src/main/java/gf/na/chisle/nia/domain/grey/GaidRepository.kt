package gf.na.chisle.nia.domain.grey

interface GaidRepository {

    suspend fun getGaid():String

}