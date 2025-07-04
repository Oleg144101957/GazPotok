package com.gasin.est.vkl.data

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import com.gasin.est.vkl.domain.grey.InstallReferrer
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class InstallReferrerImpl @Inject constructor(private val context: Context) : InstallReferrer {

    override suspend fun fetchReferrer(): String? = withContext(Dispatchers.IO) {
        suspendCancellableCoroutine { continuation ->
            val referrerClient = InstallReferrerClient.newBuilder(context).build()

            referrerClient.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    val instReferrer = referrerClient.installReferrer.installReferrer

                    when (responseCode) {
                        InstallReferrerClient.InstallReferrerResponse.OK -> {
                            try {
                                val sanitizedCampaign = instReferrer.trim()
                                val reffer = android.util.Base64.encodeToString(
                                    sanitizedCampaign.toByteArray(), android.util.Base64.NO_WRAP
                                )
                                continuation.resume(reffer)

                            } catch (e: Exception) {
                                continuation.resumeWithException(e)
                            } finally {
                                referrerClient.endConnection()
                            }
                        }

                        InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                            continuation.resumeWithException(Exception("Install Referrer API не поддерживается."))
                        }

                        InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                            continuation.resumeWithException(Exception("Сервис Install Referrer недоступен."))
                        }

                        else -> {
                            continuation.resumeWithException(Exception("Неизвестная ошибка подключения: код $responseCode"))
                        }
                    }
                }

                override fun onInstallReferrerServiceDisconnected() {
                    continuation.resumeWithException(Exception("Сервис Install Referrer отключен."))
                }
            })
        }
    }
}
