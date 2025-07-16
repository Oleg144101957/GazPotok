package com.gos.inv.est.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gos.inv.est.info.data.ErrorMessage
import com.gos.inv.est.info.domain.grey.PostErrorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val postErrorApi: PostErrorRepository) :
    ViewModel() {

    fun sendError(errorMessage: ErrorMessage) {
        viewModelScope.launch {
            postErrorApi.postError(errorMessage)
        }
    }

}