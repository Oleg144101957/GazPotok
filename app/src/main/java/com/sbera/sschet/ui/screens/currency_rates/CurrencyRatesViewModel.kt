package com.sbera.sschet.ui.screens.currency_rates

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbera.sschet.data.RemoteConfigRepoImpl
import com.sbera.sschet.domain.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(
    private val remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    var euroPrice by mutableDoubleStateOf(1.138)
        private set
    var dollarPrice by mutableDoubleStateOf(1.0)
        private set
    var liraPrice by mutableDoubleStateOf(0.0261)
        private set

    fun loadData(context: Context) {
        viewModelScope.launch {
            val data = remoteConfigRepo.takeDataFromADistantStorage(context)
            euroPrice = data[RemoteConfigRepoImpl.Companion.EURO]?.toDoubleOrNull() ?: 1.138
            dollarPrice = data[RemoteConfigRepoImpl.Companion.DOLLAR]?.toDoubleOrNull() ?: 1.0
            liraPrice = data[RemoteConfigRepoImpl.Companion.LIRA]?.toDoubleOrNull() ?: 0.0261
        }
    }
}
