package com.example.webquiz.controller

import com.example.webquiz.controller.request.ChatRequest
import com.example.webquiz.controller.request.QuizAnswerRequest
import com.example.webquiz.service.GameService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(
    private val gameService: GameService
) {

    @MessageMapping("/chat")
    fun chat(chatRequest: ChatRequest) {
        gameService.publishChat(chatRequest)
    }

    @MessageMapping("/quiz/answer")
    fun quizAnswer(chatRequest: QuizAnswerRequest) {
        gameService.publishQuiz(chatRequest)
    }
}