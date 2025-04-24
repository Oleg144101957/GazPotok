package com.bayka.capitfin.ui.screens.currency_rates

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayka.capitfin.data.RemoteConfigRepoImpl
import com.bayka.capitfin.domain.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(
    private val remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    var euroPrice by mutableStateOf(1.138)
        private set
    var dollarPrice by mutableStateOf(1.0)
        private set
    var liraPrice by mutableStateOf(0.0261)
        private set

    fun loadData(context: Context) {
        viewModelScope.launch {
            val data = remoteConfigRepo.takeDataFromADistantStorage(context)
            euroPrice = data[RemoteConfigRepoImpl.EURO]?.toDoubleOrNull() ?: 1.138
            dollarPrice = data[RemoteConfigRepoImpl.DOLLAR]?.toDoubleOrNull() ?: 1.0
            liraPrice = data[RemoteConfigRepoImpl.LIRA]?.toDoubleOrNull() ?: 0.0261
        }
    }
}
