package com.gasin.est.vkl.ui.screens.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gasin.est.vkl.data.AimBuilder
import com.gasin.est.vkl.data.DataStoreImpl.Companion.EMPTY
import com.gasin.est.vkl.data.PushServiceInitializerImpl
import com.gasin.est.vkl.data.RemoteConfigRepoImpl
import com.gasin.est.vkl.domain.grey.AppsRepository
import com.gasin.est.vkl.domain.grey.DataStore
import com.gasin.est.vkl.domain.grey.FBAttrProvider
import com.gasin.est.vkl.domain.grey.GaidRepository
import com.gasin.est.vkl.domain.grey.InstallReferrer
import com.gasin.est.vkl.domain.grey.LoadingState
import com.gasin.est.vkl.domain.grey.NetworkCheckerRepository
import com.gasin.est.vkl.domain.grey.PushServiceInitializer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val networkCheckerRepository: NetworkCheckerRepository,
    private val appsRepository: AppsRepository,
    private val fbAttrProvider: FBAttrProvider,
    private val gaidRepository: GaidRepository,
    private val pushServiceInitializerImpl: PushServiceInitializerImpl,
    private val remoteConfigRepoImpl: RemoteConfigRepoImpl,
    private val installReferrer: InstallReferrer
) : ViewModel() {

    private val mutableLiveState = MutableStateFlow<LoadingState>(LoadingState.InitState)
    val liveState: StateFlow<LoadingState> = mutableLiveState

    private val permissionState = MutableStateFlow<Boolean?>(null)

    fun getState(context: Context) {
        viewModelScope.launch {
            if (networkCheckerRepository.isConnectionAvailable()) {
                val urlFromStorage = dataStore.getUrl()
                if (urlFromStorage == EMPTY) {
                    val referrer = installReferrer.fetchReferrer()
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
                            .setZuckerberg(fbdata).setInstallReferrer(referrer.toString()).build()
                    permissionState.collect {
                        if (it != null) {
                            dataStore.saveUrl(finalUrl)
                            Log.d("123123", finalUrl)
                            mutableLiveState.emit(LoadingState.ContentState(finalUrl))
                        }
                    }
                } else {
                    mutableLiveState.emit(LoadingState.ContentState(urlFromStorage))
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

}