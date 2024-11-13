package com.example.webquiz.controller

import com.example.webquiz.domain.QuizMessage
import com.example.webquiz.service.QuizService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizSocketController(
    private val quizService: QuizService
) {

    @MessageMapping("{gameRoomId}/quiz/submit")
    @SendTo("{gameRoomId}/topic/quiz")
    fun submitAnswer(message: QuizMessage, @DestinationVariable gameRoomId: Long): Boolean {
        return quizService.submitAnswer(message.quizId, message.answer, gameRoomId)
    }
}