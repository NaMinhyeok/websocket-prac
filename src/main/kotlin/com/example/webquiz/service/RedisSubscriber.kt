package com.example.webquiz.service

import com.example.webquiz.domain.chat.Chat
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class RedisSubscriber(
    private val objectMapper: ObjectMapper,
    private val messagingTemplate: SimpMessagingTemplate
) {

    fun gameMessage(publishMessage: String) {
        // TODO: Implement game message handling
    }

    fun chatMessage(publishMessage: String) {
        val chat: Chat = objectMapper.readValue(publishMessage, Chat::class.java)
        messagingTemplate.convertAndSend(
            "/topic/chat/room/${chat.roomId}", chat
        )
    }

}