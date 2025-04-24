package com.bayka.capitfin.domain

interface DataStore {

    fun saveGoals(goals: List<String>)
    fun loadGoals(): List<String>

}