package com.example.webquiz.controller

import com.example.webquiz.domain.GameRoom
import com.example.webquiz.service.GameRoomService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(
    private val gameRoomService: GameRoomService
) {

    @MessageMapping("/game/create")
    @SendTo("/topic/games")
    fun createGame(): GameRoom {
        return gameRoomService.createGame()
    }

}
