package ru.vt.bassis.tant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.vt.bassis.tant.data.ErrorMessage
import ru.vt.bassis.tant.iv.domain.PostErrorRepository
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