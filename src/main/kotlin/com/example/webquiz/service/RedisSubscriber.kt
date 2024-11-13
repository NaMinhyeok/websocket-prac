package com.example.webquiz.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class RedisSubscriber(
    private val objectMapper: ObjectMapper,
    private val messagingTemplate: SimpMessagingTemplate
) {

    fun message(publishMessage: String) {
        val jsonNode: JsonNode = objectMapper.readTree(publishMessage)
        val roomId: String = jsonNode.get("roomId").asText()
        messagingTemplate.convertAndSend(
            "/topic/chat/room/$roomId", publishMessage
        )
    }

}