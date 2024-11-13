package com.example.webquiz.service

import com.example.webquiz.domain.chat.Chat
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisPublisher(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper,
    @Qualifier("gameTopic") private val gameTopic: ChannelTopic,
    @Qualifier("chatTopic") private val chatTopic: ChannelTopic
) {

    fun publishGame(message: Any) {
        redisTemplate.convertAndSend(gameTopic.topic, message)
    }

    fun publishChat(chat: Chat) {
        val chatJson = objectMapper.writeValueAsString(chat)
        redisTemplate.convertAndSend(chatTopic.topic, chatJson)
    }

}