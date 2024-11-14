package com.example.webquiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("gameRoom")
class GameRoom(
    @Id
    val id: String? = null,
    var playerCount: Int = 0,
    var currentQuizIndex : Int = 0,
    var status: GameStatus = GameStatus.WAITING
) {

    fun join() {
        playerCount++
    }

    fun leave() {
        playerCount--
    }
}
