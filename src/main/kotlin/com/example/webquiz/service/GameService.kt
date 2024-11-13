package com.example.webquiz.service

import com.example.webquiz.controller.request.ChatRequest
import com.example.webquiz.controller.request.QuizAnswerRequest
import com.example.webquiz.domain.message.ChatMessage
import com.example.webquiz.domain.message.QuizMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    private val redisPublisher: RedisPublisher

) {

    @Transactional
    fun publishChat(request: ChatRequest) {
        val message = ChatMessage(
            roomId = request.roomId,
            message = request.message
        )
        redisPublisher.publish(message)
    }

    @Transactional
    fun publishQuiz(request: QuizAnswerRequest) {
        val message = QuizMessage(
            roomId = request.roomId,
            quizId = request.quizId,
            answer = request.answer
        )
        redisPublisher.publish(message)
    }

}