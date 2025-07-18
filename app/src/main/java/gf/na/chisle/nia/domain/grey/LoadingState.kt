package gf.na.chisle.nia.domain.grey

sealed class LoadingState {

    data object InitState : LoadingState()
    data object NoNetworkState : LoadingState()
    data class ContentState(val url: String) : LoadingState()

}