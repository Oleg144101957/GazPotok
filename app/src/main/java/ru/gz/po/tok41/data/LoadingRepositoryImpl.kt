package ru.gz.po.tok41.data

import android.content.Context
import android.util.Log
import com.moneocomp.tpao.data.RemoteConfigRepoImpl
import ru.gz.po.tok41.domain.grey.AdjustAttr
import ru.gz.po.tok41.domain.grey.GaidRepository
import ru.gz.po.tok41.domain.grey.InstallReferrer
import ru.gz.po.tok41.domain.grey.LoadingRepository
import org.json.JSONObject
import ru.gz.po.tok41.data.DataStoreImpl.Companion.EMPTY
import ru.gz.po.tok41.domain.grey.DataStore
import ru.gz.po.tok41.domain.grey.PushServiceInitializer
import javax.inject.Inject

class LoadingRepositoryImpl @Inject constructor(
    private val gaidRepository: GaidRepository,
    private val adjustAttr: AdjustAttr,
    private val remoteConfigRepoImpl: RemoteConfigRepoImpl,
    private val installReferrer: InstallReferrer,
    private val dataStore: DataStore,
    private val pushServiceInitializer: PushServiceInitializer
) : LoadingRepository {

    override suspend fun loadUrl(token: String, context: Context): String {
        val urlFromStorage = dataStore.getUrl()
        return if (urlFromStorage == EMPTY) {
            val gaid = gaidRepository.getGaid()
            pushServiceInitializer.initializePushService(gaid, context)

            val adjustParams = adjustAttr.getAdjustAttr(token, context)
            val data = remoteConfigRepoImpl.takeDataFromADistantStorage(context)
            val domain = data[RemoteConfigRepoImpl.DOMAIN] ?: "https://defaultdomain.com"
            val referrer = installReferrer.fetchReferrer()

            val jsonResponse = adjustParams["jsonResponse"] ?: ""

            val parsedAdjustParams = parseAdjustJson(jsonResponse)

            val finalAdjustParams =
                adjustParams.filterKeys { it != "jsonResponse" } + parsedAdjustParams

            val finalUrl = AimBuilder()
                .setGaid(gaid)
                .setInstallReferrer(referrer.toString())
                .setAdjustParams(finalAdjustParams)
                .build(domain)

            Log.v("123123", "Builded: $finalUrl")
            dataStore.saveUrl(finalUrl)
            finalUrl
        } else {
            Log.v("123123", "From Storage: $urlFromStorage")
            urlFromStorage
        }
    }

    private fun parseAdjustJson(jsonString: String): Map<String, String> {
        if (jsonString.isBlank()) return emptyMap()

        val json = JSONObject(jsonString)
        val keys = json.keys()
        val result = mutableMapOf<String, String>()

        while (keys.hasNext()) {
            val key = keys.next()
            val value = json.opt(key)?.toString() ?: ""
            if (value.isNotBlank()) {
                val formattedKey = when (key) {
                    "tracker_token" -> "trackerToken"
                    "tracker_name" -> "trackerName"
                    else -> key
                }
                result[formattedKey] = value
            }
        }
        return result
    }
}
