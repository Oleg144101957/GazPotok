package com.sb.er.act.iv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.er.act.iv.data.ErrorMessage
import com.sb.er.act.iv.domain.PostErrorRepository
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