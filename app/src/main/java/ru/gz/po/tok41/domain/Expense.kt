package ru.gz.po.tok41.domain

data class Expense(
    val amount: String,
    val description: String,
    val category: String,
    val photoUri: String,
    val timestamp: Long
)