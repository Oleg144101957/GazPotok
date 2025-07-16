package com.sb.er.act.iv.domain.grey

interface DataStore {
    fun saveGoals(goals: List<String>)
    fun loadGoals(): List<String>
    fun saveUrl(newGoalToSave: String)
    fun getUrl(): String

    fun saveExpenses(expenses: List<String>)
    fun loadExpenses(): List<String>

}