package ru.tan.alitic.rch.domain.grey

sealed class LoadingState {

    data object InitState : LoadingState()
    data object NoNetworkState : LoadingState()
    data class ContentState(val url: String) : LoadingState()
    data object WhiteState : LoadingState()

}