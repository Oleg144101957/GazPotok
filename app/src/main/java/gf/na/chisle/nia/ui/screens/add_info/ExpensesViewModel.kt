package gf.na.chisle.nia.ui.screens.add_info

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gf.na.chisle.nia.domain.Expense
import gf.na.chisle.nia.domain.grey.DataStore
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    init {
        loadExpenses()
    }

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
}
