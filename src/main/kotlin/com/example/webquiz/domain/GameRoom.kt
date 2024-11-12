package com.example.webquiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("gameRoom")
class GameRoom(
    @Id
    val gameId: Long,
    var playerCount: Int = 0,
    var status: GameStatus = GameStatus.WAITING
) : Serializable {

    fun join() {
        playerCount++
    }

    fun leave() {
        playerCount--
    }

    companion object {
        fun create(
            gameId: Long,
        ) = GameRoom(
            gameId = gameId,
            playerCount = 1,
            status = GameStatus.WAITING
        )
    }
}
