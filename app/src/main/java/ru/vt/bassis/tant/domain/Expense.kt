package ru.vt.bassis.tant.domain

data class Expense(
    val amount: String,
    val description: String,
    val category: String,
    val photoUri: String,
    val timestamp: Long
)