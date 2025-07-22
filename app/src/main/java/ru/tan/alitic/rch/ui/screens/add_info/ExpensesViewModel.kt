package ru.tan.alitic.rch.ui.screens.add_info

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tan.alitic.rch.domain.Expense
import ru.tan.alitic.rch.domain.grey.DataStore
import javax.inject.Inject
@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    // Траты
    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    // Черный список трат
    var unwantedExpenses = mutableStateListOf<String>()
        private set

    init {
        loadExpenses()
        loadUnwantedExpenses()
    }

    // --- Траты ---
    fun saveExpense(expense: Expense) {
        _expenses.add(expense)
        val serialized = _expenses.map {
            "${it.amount};;${it.description};;${it.category};;${it.photoUri};;${it.timestamp}"
        }
        dataStore.saveExpenses(serialized)
    }

    private fun loadExpenses() {
        val list = dataStore.loadExpenses()
        _expenses.clear()
        _expenses.addAll(list.map {
            val parts = it.split(";;")
            Expense(
                amount = parts.getOrNull(0).orEmpty(),
                description = parts.getOrNull(1).orEmpty(),
                category = parts.getOrNull(2).orEmpty(),
                photoUri = parts.getOrNull(3).orEmpty(),
                timestamp = parts.getOrNull(4)?.toLongOrNull() ?: 0L
            )
        })
    }

    private fun loadUnwantedExpenses() {
        unwantedExpenses.clear()
        unwantedExpenses.addAll(dataStore.loadUnwantedExpenses())
    }
}

