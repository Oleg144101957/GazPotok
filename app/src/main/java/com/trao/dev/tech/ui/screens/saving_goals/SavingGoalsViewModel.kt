package com.trao.dev.tech.ui.screens.saving_goals

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.trao.dev.tech.domain.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavingGoalsViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    private val _savingsList = mutableStateListOf<String>()
    val savingsList: List<String> get() = _savingsList

    init {
        loadGoals()
    }

    fun loadGoals() {
        _savingsList.clear()
        _savingsList.addAll(dataStore.loadGoals())
    }

    fun saveGoal(goal: String, index: Int? = null) {
        val parts = goal.split("—", "/").map { it.trim() }
        val added = parts.getOrNull(1)?.toDoubleOrNull() ?: 0.0
        val total = parts.getOrNull(2)?.toDoubleOrNull() ?: Double.MAX_VALUE

        if (added >= total) {
            if (index != null && index in _savingsList.indices) {
                _savingsList.removeAt(index)
            }
        } else {
            if (index != null && index in _savingsList.indices) {
                _savingsList[index] = goal
            } else {
                _savingsList.add(goal)
            }
        }
        dataStore.saveGoals(_savingsList)
    }


}

