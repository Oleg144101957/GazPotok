package com.trao.dev.tech.ui.screens.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trao.dev.tech.data.AimBuilder
import com.trao.dev.tech.data.PushServiceInitializerImpl
import com.trao.dev.tech.data.RemoteConfigRepoImpl
import com.trao.dev.tech.domain.AppsRepository
import com.trao.dev.tech.domain.DataStore
import com.trao.dev.tech.domain.FBAttrProvider
import com.trao.dev.tech.domain.GaidRepository
import com.trao.dev.tech.domain.InstallReferrer
import com.trao.dev.tech.domain.LoadingState
import com.trao.dev.tech.domain.NetworkCheckerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val networkCheckerRepository: NetworkCheckerRepository,
    private val fbAttrProvider: FBAttrProvider,
    private val gaidRepository: GaidRepository,
    private val pushServiceInitializerImpl: PushServiceInitializerImpl,
    private val remoteConfigRepoImpl: RemoteConfigRepoImpl,
    private val installReferrer: InstallReferrer,
    private val appsRepository: AppsRepository

) : ViewModel() {
    private val mutableLiveState = MutableStateFlow<LoadingState>(LoadingState.InitState)
    val liveState: StateFlow<LoadingState> = mutableLiveState

    private val permissionState = MutableStateFlow<Boolean?>(null)

    fun load(context: Context) {
        viewModelScope.launch {
            val network = networkCheckerRepository.isConnectionAvailable()
            if (network) {
                val installReferrer = installReferrer.fetchReferrer()
                val urlFromStorage = dataStore.getUrl()
                if (urlFromStorage == EMPTY) {
                    val gaid = gaidRepository.getGaid()
                    pushServiceInitializerImpl.initializePushService(gaid, context)
                    val data = remoteConfigRepoImpl.takeDataFromADistantStorage(context)
                    val configAppID = data[RemoteConfigRepoImpl.ZUCKERBERGID] ?: "defaultAppID"
                    val configAppToken =
                        data[RemoteConfigRepoImpl.ZUCKERBERGACCESSTOKEN] ?: "defaultAppToken"
                    val configDevKey = data[RemoteConfigRepoImpl.APPSATTRDEVKEY] ?: "defaultDevKey"
                    val uuid = appsRepository.provideDIUU(context)
                    val appsdata = appsRepository.provideFlyer(context, configDevKey)
                    val fbdata = fbAttrProvider.provideFB(context, configAppID, configAppToken)
                    val finalUrl =
                        AimBuilder().setBrinID(gaid).setAppsData(appsdata).setAppsIdentif(uuid)
                            .setZuckerberg(fbdata)
                            .setInstallReferrer(referrer = installReferrer.toString()).build()
                    permissionState.collect {
                        if (it != null) {
                            dataStore.saveUrl(finalUrl)
                            Log.v("123123", finalUrl)
                            mutableLiveState.emit(LoadingState.ContentState(url = finalUrl))
                        }
                    }
                } else {
                    Log.v("123123", "from storage: $urlFromStorage")
                    mutableLiveState.emit(LoadingState.ContentState(url = urlFromStorage))
                }
            } else {
                mutableLiveState.emit(LoadingState.NoNetworkState)
            }

        }
    }

    fun updatePermissionState(isGranted: Boolean) {
        viewModelScope.launch {
            permissionState.emit(isGranted)
        }
    }

    companion object {
        private const val EMPTY = "EMPTY"
    }
}