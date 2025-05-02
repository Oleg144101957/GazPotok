package com.sbera.sschet.ui.screens.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbera.sschet.domain.LoadingRepository
import com.sbera.sschet.domain.LoadingState
import com.sbera.sschet.domain.NetworkCheckerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkCheckerRepository: NetworkCheckerRepository,
    private val loadingRepository: LoadingRepository,
) : ViewModel() {

    private val mutableLiveState = MutableStateFlow<LoadingState>(LoadingState.InitState)
    val liveState: StateFlow<LoadingState> = mutableLiveState

    private val permissionState = MutableStateFlow<Boolean?>(null)

    fun load(context: Context, token: String) {
        viewModelScope.launch {
            val network = networkCheckerRepository.isConnectionAvailable()
            if (network) {
                val url = loadingRepository.loadUrl(token, context)
                permissionState.collect {
                    if (it != null) {
                        mutableLiveState.emit(LoadingState.ContentState(url = url))
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