package com.gasin.est.vkl.domain

data class QuizQuestion(
    val question: String,
    val correctAnswer: Boolean,
    val explanationIfCorrect: String,
    val explanationIfWrong: String
)
