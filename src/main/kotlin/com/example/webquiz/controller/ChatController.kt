package com.example.webquiz.controller

import com.example.webquiz.controller.request.ChatRequest
import com.example.webquiz.controller.request.QuizAnswerRequest
import com.example.webquiz.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val chatService: ChatService
) {

    @MessageMapping("/chat")
    fun chat(chatRequest: ChatRequest) {
        chatService.publishChat(chatRequest)
    }

    @MessageMapping("/quiz/answer")
    fun quizAnswer(chatRequest: QuizAnswerRequest) {
        chatService.publishQuiz(chatRequest)
    }
}