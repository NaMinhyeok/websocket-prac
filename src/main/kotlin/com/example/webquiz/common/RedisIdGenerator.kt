package com.example.webquiz.common

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class RedisIdGenerator(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun generateGameRoomID(key: String): String {
        return redisTemplate.opsForValue()
            .increment("{$key}:sequence")
            ?.toString()
            ?: throw IllegalStateException("ID 생성 실패")
    }
}