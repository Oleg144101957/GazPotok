package com.sbe.rand.inve.sttwo.data

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.gosapp.sberkas.sber.domain.RemoteConfigRepo
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
                        val euro = unrealRemote.getString(EURO)
                        val dollar = unrealRemote.getString(DOLLAR)
                        val lira = unrealRemote.getString(LIRA)
                        val appsAttrDevKey = unrealRemote.getString(APPSATTRDEVKEY)
                        val domain = unrealRemote.getString(DOMAIN)

                        val mutableMapWithDataFromRemoteConfig = mutableMapOf<String, String>()
                        mutableMapWithDataFromRemoteConfig[ZUCKERBERGID] = zuckerbergID
                        mutableMapWithDataFromRemoteConfig[ZUCKERBERGACCESSTOKEN] =
                            zuckerbergAccessToken
                        mutableMapWithDataFromRemoteConfig[GOLD] = gold
                        mutableMapWithDataFromRemoteConfig[SILVER] = silver
                        mutableMapWithDataFromRemoteConfig[PLATINUM] = platinum
                        mutableMapWithDataFromRemoteConfig[EURO] = euro
                        mutableMapWithDataFromRemoteConfig[DOLLAR] = dollar
                        mutableMapWithDataFromRemoteConfig[LIRA] = lira
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
        const val EURO = "euro"
        const val DOLLAR = "dollar"
        const val LIRA = "lira"
        const val ZUCKERBERGID = "trao_app_id"
        const val ZUCKERBERGACCESSTOKEN = "trao_client_token"
        const val APPSATTRDEVKEY = "trao_appsflyer_key"
        const val DOMAIN = "deferred_link"
    }
}