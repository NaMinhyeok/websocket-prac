package com.example.webquiz.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: String): String {
        return "Hello, $message!"
    }
}
