package ru.tan.alitic.rch.ui.screens.unwatned_expenses

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tan.alitic.rch.domain.grey.DataStore
import javax.inject.Inject

@HiltViewModel
class UnwantedExpensesViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    var unwantedExpenses = mutableStateListOf<String>()
        private set

    init {
        loadUnwantedExpenses()
    }

    fun addExpense(item: String) {
        unwantedExpenses.add(item)
        save()
    }

    fun removeExpense(item: String) {
        unwantedExpenses.remove(item)
        save()
    }

    private fun save() {
        dataStore.saveUnwantedExpenses(unwantedExpenses)
    }

    private fun loadUnwantedExpenses() {
        unwantedExpenses.clear()
        unwantedExpenses.addAll(dataStore.loadUnwantedExpenses())
    }
}
