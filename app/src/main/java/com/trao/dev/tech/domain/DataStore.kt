package com.trao.dev.tech.domain

interface DataStore {
    fun saveGoals(goals: List<String>)
    fun loadGoals(): List<String>
}