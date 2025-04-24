package com.bayka.capitfin.ui.screens.investment_calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayka.capitfin.data.RemoteConfigRepoImpl
import com.bayka.capitfin.domain.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentCalculatorViewModel @Inject constructor(private val remoteConfigRepo: RemoteConfigRepo) :
    ViewModel() {

    fun loadData(context: Context) {
        viewModelScope.launch {
            val data = remoteConfigRepo.takeDataFromADistantStorage(context)
            val gold = data[RemoteConfigRepoImpl.GOLD] ?: "40"
            val silver = data[RemoteConfigRepoImpl.SILVER] ?: "40"
            val platinum = data[RemoteConfigRepoImpl.PLATINUM] ?: "40"
        }
    }

}