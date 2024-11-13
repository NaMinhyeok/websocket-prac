package com.example.webquiz.service

import com.example.webquiz.domain.message.BaseMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisPublisher(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper,
    private val gameTopic: ChannelTopic,
) {

    fun publish(message: BaseMessage) {
        val messageJson = objectMapper.writeValueAsString(message)
        redisTemplate.convertAndSend(gameTopic.topic, messageJson)
    }

}