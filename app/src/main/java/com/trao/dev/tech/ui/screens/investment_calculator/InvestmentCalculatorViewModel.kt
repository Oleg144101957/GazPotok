package com.trao.dev.tech.ui.screens.investment_calculator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trao.dev.tech.data.RemoteConfigRepoImpl
import com.trao.dev.tech.domain.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentCalculatorViewModel @Inject constructor(
    private val remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    var goldPrice by mutableDoubleStateOf(10.742)
        private set
    var silverPrice by mutableDoubleStateOf(1.07)
        private set
    var platinumPrice by mutableDoubleStateOf(32.02)
        private set

    fun loadData(context: Context) {
        viewModelScope.launch {
            val data = remoteConfigRepo.takeDataFromADistantStorage(context)
            goldPrice = data[RemoteConfigRepoImpl.Companion.GOLD]?.toDoubleOrNull() ?: 10.742
            silverPrice = data[RemoteConfigRepoImpl.Companion.SILVER]?.toDoubleOrNull() ?: 1.07
            platinumPrice = data[RemoteConfigRepoImpl.Companion.PLATINUM]?.toDoubleOrNull() ?: 32.02
        }
    }
}
