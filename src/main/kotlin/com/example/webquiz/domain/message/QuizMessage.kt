package com.example.webquiz.domain.message

class QuizMessage(
    override val roomId: Long,
    override val type: String = "quiz",
    val quizId: Long,
    val answer: Boolean
) : BaseMessage {
}