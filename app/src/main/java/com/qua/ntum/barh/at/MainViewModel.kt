package com.qua.ntum.barh.at

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qua.ntum.barh.at.data.ErrorMessage
import com.qua.ntum.barh.at.domain.grey.PostErrorRepository
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