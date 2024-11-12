package com.example.webquiz.service

import com.example.webquiz.domain.GameRoom
import com.example.webquiz.domain.GameRoomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class GameRoomService(
    private val gameRoomRepository: GameRoomRepository
) {
    fun createGame(): GameRoom {
        val gameId = 1L
        val gameRoom = GameRoom.create(gameId)

        return gameRoomRepository.save(gameRoom)
    }

    fun joinGame(gameId: Long): GameRoom {
        val gameRoom = getGameRoom(gameId)
        gameRoom.join()
        return gameRoom
    }

    fun leaveGame(gameId: Long): GameRoom {
        val gameRoom = getGameRoom(gameId)
        gameRoom.leave()
        return gameRoom
    }

    private fun getGameRoom(gameId: Long): GameRoom {
        return gameRoomRepository.findById(gameId)
            .orElseThrow(::IllegalArgumentException)
    }
}
