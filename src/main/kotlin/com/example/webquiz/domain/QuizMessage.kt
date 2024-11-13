package com.example.webquiz.domain

class QuizMessage(
    val type: MessageType,
    val quizId: Long,
    val answer: Boolean
) {
}