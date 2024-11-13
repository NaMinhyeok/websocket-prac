package com.example.webquiz.service

import com.example.webquiz.controller.request.ChatRequest
import com.example.webquiz.controller.request.QuizAnswerRequest
import com.example.webquiz.domain.chat.Chat
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val redisPublisher: RedisPublisher

) {

    @Transactional
    fun publishChat(request: ChatRequest) {
        val chat = Chat(
            request.roomId,
            request.sender,
            request.message
        )
        redisPublisher.publishChat(chat)
    }

    @Transactional
    fun publishQuiz(request: QuizAnswerRequest) {
        redisPublisher.publishChat(request)
    }

}