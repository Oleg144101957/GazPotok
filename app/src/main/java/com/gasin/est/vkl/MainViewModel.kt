package com.gasin.est.vkl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gasin.est.vkl.data.ErrorMessage
import com.gasin.est.vkl.domain.grey.PostErrorRepository
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