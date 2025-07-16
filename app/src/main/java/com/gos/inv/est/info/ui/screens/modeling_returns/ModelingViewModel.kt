package com.gos.inv.est.info.ui.screens.modeling_returns

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gos.inv.est.info.domain.grey.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelingViewModel @Inject constructor(
    private val remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    private val _averageReturn = mutableStateOf<Float?>(null)
    val averageReturn: State<Float?> = _averageReturn

    private val _selectedMonths = MutableStateFlow<Int?>(null)
    val selectedMonths: StateFlow<Int?> = _selectedMonths

    private val _calculatedReturn = MutableStateFlow<Float?>(null)
    val calculatedReturn: StateFlow<Float?> = _calculatedReturn


    fun loadReturnsFor(context: Context, type: String) {
        viewModelScope.launch {
            val remoteData = remoteConfigRepo.takeDataFromADistantStorage(context)
            val typeString = remoteData[type]
            val avg = typeString?.toFloatOrNull() ?: 7f

            Log.v("123123", "Average for $type: $avg")
            _averageReturn.value = avg

            _selectedMonths.value?.let { months ->
                calculateReturn(months)
            }
        }
    }

    fun setSelectedMonths(months: Int) {
        _selectedMonths.value = months
        calculateReturn(months)
    }

    private fun calculateReturn(months: Int) {
        val avg12 = _averageReturn.value ?: return
        val monthly = avg12 / 12f
        val total = monthly * months
        _calculatedReturn.value = total
    }

    fun calculateReturn(amount: Float) {
        val calcReturn = _calculatedReturn.value ?: return
        val profit = calcReturn * amount / 100f
        Log.d("ModelingVM", "Calculated profit: $profit")
    }
}



