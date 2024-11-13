package com.example.webquiz.config

import com.example.webquiz.common.logger
import com.example.webquiz.domain.MessageType
import com.example.webquiz.domain.QuizMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class QuizMessageListener(
    private val objectMapper: ObjectMapper,
    private val simpMessagingTemplate: SimpMessagingTemplate
) : MessageListener {

    val log = logger()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val quizMessage = objectMapper.readValue(message.body, QuizMessage::class.java)
            // 메시지 타입에 따른 처리
            when (quizMessage.type) {
                MessageType.SUBMIT_ANSWER -> handleSubmitAnswer(quizMessage)
                MessageType.QUIZ_START -> TODO()
                MessageType.QUIZ_END -> TODO()
            }
        } catch (e: Exception) {
            log.info("메시지 처리 중 오류 발생", e)
        }
    }

    private fun handleSubmitAnswer(message: QuizMessage) {
        try {
            val gameRoomId = message.quizId  // 또는 별도 gameRoomId 필드가 필요
            val isCorrect = message.answer

            // WebSocket을 통해 구독자들에게 결과 전송
            simpMessagingTemplate.convertAndSend(
                "/topic/quiz/$gameRoomId",
                isCorrect
            )
        } catch (e: Exception) {
            log.error("답변 처리 중 오류 발생", e)
        }
    }
}