package com.example.webquiz.service

import com.example.webquiz.common.logger
import com.example.webquiz.controller.request.ChatRequest
import com.example.webquiz.domain.chat.Chat
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val redisPublisher: RedisPublisher

) {

    val log = logger()

    @Transactional
    fun publishChat(request: ChatRequest) {
        val chat = Chat(
            request.roomId,
            request.sender,
            request.message
        )
        redisPublisher.publishChat(chat)
    }

}