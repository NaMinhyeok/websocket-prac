package com.example.webquiz.service

import com.example.webquiz.common.RedisIdGenerator
import com.example.webquiz.domain.GameRoom
import com.example.webquiz.domain.GameRoomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class GameRoomService(
    private val gameRoomRepository: GameRoomRepository,
    private val redisIdGenerator: RedisIdGenerator
) {
    fun createGame(): GameRoom {
        val gameRoomId = redisIdGenerator.generateGameRoomID("gameRoom")
        val gameRoom = GameRoom(id = gameRoomId)

        return gameRoomRepository.save(gameRoom)
    }

    fun joinGame(gameId: String): GameRoom {
        val gameRoom = getGameRoom(gameId)
        gameRoom.join()
        return gameRoom
    }

    fun leaveGame(gameId: String): GameRoom {
        val gameRoom = getGameRoom(gameId)
        gameRoom.leave()
        return gameRoom
    }

    private fun getGameRoom(gameId: String): GameRoom {
        return gameRoomRepository.findById(gameId)
            .orElseThrow(::IllegalArgumentException)
    }
}
