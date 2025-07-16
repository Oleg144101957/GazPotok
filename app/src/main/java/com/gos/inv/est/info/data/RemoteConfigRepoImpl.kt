package com.gos.inv.est.info.data

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.gos.inv.est.info.domain.grey.RemoteConfigRepo
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteConfigRepoImpl @Inject constructor() : RemoteConfigRepo {

    override suspend fun takeDataFromADistantStorage(context: Context): Map<String, String> =
        suspendCoroutine { cont ->

            val unrealRemote = Firebase.remoteConfig

            unrealRemote.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val zuckerbergID = unrealRemote.getString(ZUCKERBERGID)
                        val zuckerbergAccessToken = unrealRemote.getString(ZUCKERBERGACCESSTOKEN)
                        val gold = unrealRemote.getString(GOLD)
                        val silver = unrealRemote.getString(SILVER)
                        val platinum = unrealRemote.getString(PLATINUM)
                        val palladium = unrealRemote.getString(PALLADIUM)
                        val s_and_p500 = unrealRemote.getString(S_AND_P500)
                        val real_estate = unrealRemote.getString(REAL_ESTATE)
                        val appsAttrDevKey = unrealRemote.getString(APPSATTRDEVKEY)
                        val domain = unrealRemote.getString(DOMAIN)

                        val mutableMapWithDataFromRemoteConfig = mutableMapOf<String, String>()
                        mutableMapWithDataFromRemoteConfig[ZUCKERBERGID] = zuckerbergID
                        mutableMapWithDataFromRemoteConfig[ZUCKERBERGACCESSTOKEN] =
                            zuckerbergAccessToken
                        mutableMapWithDataFromRemoteConfig[GOLD] = gold
                        mutableMapWithDataFromRemoteConfig[SILVER] = silver
                        mutableMapWithDataFromRemoteConfig[PLATINUM] = platinum
                        mutableMapWithDataFromRemoteConfig[PALLADIUM] = palladium
                        mutableMapWithDataFromRemoteConfig[REAL_ESTATE] = real_estate
                        mutableMapWithDataFromRemoteConfig[S_AND_P500] = s_and_p500
                        mutableMapWithDataFromRemoteConfig[APPSATTRDEVKEY] = appsAttrDevKey
                        mutableMapWithDataFromRemoteConfig[DOMAIN] = domain


                        Log.v("123123", "$mutableMapWithDataFromRemoteConfig")
                        cont.resume(mutableMapWithDataFromRemoteConfig)
                    } else {
                        cont.resume(emptyMap())
                    }
                }
        }

    companion object {
        const val GOLD = "gold"
        const val SILVER = "silver"
        const val PLATINUM = "platinum"
        const val PALLADIUM = "palladium"
        const val S_AND_P500 = "s_and_p500"
        const val REAL_ESTATE = "real_estate"
        const val ZUCKERBERGID = "gos_in_app_id"
        const val ZUCKERBERGACCESSTOKEN = "gos_in_app_token"
        const val APPSATTRDEVKEY = "gos_in_appsflyer"
        const val DOMAIN = "deferred_link"
    }
}