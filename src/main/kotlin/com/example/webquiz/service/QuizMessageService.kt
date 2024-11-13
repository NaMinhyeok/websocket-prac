package com.example.webquiz.service

import com.example.webquiz.common.logger
import com.example.webquiz.domain.MessageType
import com.example.webquiz.domain.QuizMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class QuizMessageService(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    val log = logger()

    private val QUIZ_CHANNEL_FORMAT = "quiz_channel:%d"

    fun publishSubmitAnswer(
        gameRoomId: Long,
        quizId: Long,
        isCorrect: Boolean
    ) {
        val message = QuizMessage(
            type = MessageType.SUBMIT_ANSWER,
            quizId = quizId,
            answer = isCorrect
        )
        publish(gameRoomId, message)
    }

    private fun publish(
        gameRoomId: Long,
        message: QuizMessage
    ) {
        try {
            val channel = String.format(QUIZ_CHANNEL_FORMAT, gameRoomId)
            val messageJson = objectMapper.writeValueAsString(message)
            redisTemplate.convertAndSend(channel, messageJson)
        } catch (e: Exception) {
            log.error("메시지 발행 중 오류 발생", e)
            throw e
        }
    }
}