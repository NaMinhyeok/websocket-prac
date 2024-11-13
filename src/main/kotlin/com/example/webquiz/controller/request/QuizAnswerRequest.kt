package com.example.webquiz.controller.request

class QuizAnswerRequest(
    val roomId: Long,
    val quizId: Long,
    val answer: Boolean
) {

}
