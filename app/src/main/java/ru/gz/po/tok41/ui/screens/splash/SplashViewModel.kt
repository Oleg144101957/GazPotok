package ru.gz.po.tok41.ui.screens.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneocomp.tpao.data.RemoteConfigRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gz.po.tok41.domain.grey.LoadingRepository
import ru.gz.po.tok41.domain.grey.LoadingState
import ru.gz.po.tok41.domain.grey.NetworkCheckerRepository
import ru.gz.po.tok41.domain.grey.RemoteConfigRepo
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkCheckerRepository: NetworkCheckerRepository,
    private val loadingRepository: LoadingRepository,
    private val remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    private val mutableLiveState = MutableStateFlow<LoadingState>(LoadingState.InitState)
    val liveState: StateFlow<LoadingState> = mutableLiveState

    private val permissionState = MutableStateFlow<Boolean?>(null)

    fun load(context: Context, token: String) {
        viewModelScope.launch {
            val network = networkCheckerRepository.isConnectionAvailable()
            if (network) {
                val data = remoteConfigRepo.takeDataFromADistantStorage(context)
                val domain = data[RemoteConfigRepoImpl.DOMAIN] ?: "defaultDomain"

                if (domain.startsWith("https")) {
                    val url = loadingRepository.loadUrl(token, context)
                    permissionState.collect {
                        if (it != null) {
                            mutableLiveState.emit(LoadingState.ContentState(url = url))
                        }
                    }
                } else {
                    Log.v("123123", "not https")
                    permissionState.collect {
                        if (it != null) {
                            mutableLiveState.emit(LoadingState.WhiteState)
                        }
                    }
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