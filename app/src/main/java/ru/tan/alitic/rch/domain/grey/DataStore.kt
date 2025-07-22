package ru.tan.alitic.rch.domain.grey

interface DataStore {
    fun saveGoals(goals: List<String>)
    fun loadGoals(): List<String>
    fun saveUrl(newGoalToSave: String)
    fun getUrl(): String

    fun saveExpenses(expenses: List<String>)
    fun loadExpenses(): List<String>

    fun saveUnwantedExpenses(expenses: List<String>)

    fun loadUnwantedExpenses(): List<String>
}