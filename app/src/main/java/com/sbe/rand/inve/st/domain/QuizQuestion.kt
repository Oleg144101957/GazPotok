package com.sbe.rand.inve.st.domain

data class QuizQuestion(
    val question: String,
    val correctAnswer: Boolean,
    val explanationIfCorrect: String,
    val explanationIfWrong: String
)
