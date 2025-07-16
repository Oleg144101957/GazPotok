package com.sb.er.act.iv.domain

data class Expense(
    val amount: String,
    val description: String,
    val category: String,
    val photoUri: String,
    val timestamp: Long
)