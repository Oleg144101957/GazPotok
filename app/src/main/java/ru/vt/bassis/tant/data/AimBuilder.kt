package ru.vt.bassis.tant.data

import android.net.Uri
import androidx.core.net.toUri

class AimBuilder {

    private var gaid: String = ""
    private var installReferrer: String = ""
    private var adjustParams: MutableMap<String, String> = mutableMapOf()

    fun setGaid(inputGaid: String) = apply { this.gaid = inputGaid }
    fun setInstallReferrer(referrer: String) = apply { this.installReferrer = referrer }


    fun setAdjustParams(params: Map<String, String>) = apply {
        this.adjustParams.clear()
        this.adjustParams.putAll(params)
    }

    fun build(baseUrl: String): String {
        val urlBuilder = baseUrl.toUri().buildUpon()

        urlBuilder.appendQueryParameter(GAD_ID_KEY, gaid)
        urlBuilder.appendQueryParameter(REFERRER, installReferrer)

        appendQueryParamForAdjust(adjustParams, urlBuilder)

        return urlBuilder.build().toString()
    }

    private fun appendQueryParamForAdjust(
        params: Map<String, String>,
        uriBuilder: Uri.Builder
    ) {
        params.forEach { (key, value) ->
            if (value.isNotEmpty()) {
                uriBuilder.appendQueryParameter(key, value)
            }
        }
    }

    companion object {
        private const val GAD_ID_KEY = "gaid"
        private const val REFERRER = "referrer"
    }
}
