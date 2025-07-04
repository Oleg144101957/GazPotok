package com.gasin.est.vkl.domain.grey

interface DataStore {
    fun saveGoals(goals: List<String>)
    fun loadGoals(): List<String>
    fun saveUrl(newGoalToSave: String)
    fun getUrl(): String
}