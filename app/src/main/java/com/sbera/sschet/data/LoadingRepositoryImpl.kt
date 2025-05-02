package com.sbera.sschet.data

import android.content.Context
import android.util.Log
import com.sbera.sschet.data.DataStoreImpl.Companion.EMPTY
import com.sbera.sschet.domain.AdjustAttr
import com.sbera.sschet.domain.DataStore
import com.sbera.sschet.domain.GaidRepository
import com.sbera.sschet.domain.InstallReferrer
import com.sbera.sschet.domain.LoadingRepository
import com.sbera.sschet.util.UrlBuilder
import javax.inject.Inject

class LoadingRepositoryImpl @Inject constructor(
    private val gaidRepository: GaidRepository,
    private val adjustAttr: AdjustAttr,
    private val remoteConfigRepoImpl: RemoteConfigRepoImpl,
    private val installReferrer: InstallReferrer,
    private val dataStore: DataStore
) : LoadingRepository {

    override suspend fun loadUrl(token: String, context: Context): String {
        val urlFromStorage = dataStore.getUrl()
        return if (urlFromStorage == EMPTY) {
            val gaid = gaidRepository.getGaid()
            val adj = adjustAttr.getAdjustAttr(token, context)
            val data = remoteConfigRepoImpl.takeDataFromADistantStorage(context)
            val domain = data[RemoteConfigRepoImpl.DOMAIN] ?: "defaultDomain"
            val referrer = installReferrer.fetchReferrer()
            val urlBuilder = UrlBuilder(domain)

            adj.forEach { (key, value) ->
                if (value.isNotEmpty()) {
                    urlBuilder.addParameter(key, value)
                }
            }
            val finalUrl = urlBuilder
                .addParameter("gaid", gaid)
                .addParameter("referrer", referrer)
                .build()
            Log.v("123123", "Builded: $finalUrl")
            dataStore.saveUrl(finalUrl)
            finalUrl
        } else {
            Log.v("123123", "From Storage: $urlFromStorage")
            urlFromStorage
        }
    }

}