package com.sbera.sschet.domain

sealed class LoadingState {

    data object InitState : LoadingState()
    data object NoNetworkState : LoadingState()
    data class ContentState(val url: String) : LoadingState()
    data object WhiteState : LoadingState()

}