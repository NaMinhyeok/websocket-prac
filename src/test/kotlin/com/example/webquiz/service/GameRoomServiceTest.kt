package com.example.webquiz.service

import com.example.webquiz.domain.GameRoom
import com.example.webquiz.domain.GameRoomRepository
import com.example.webquiz.domain.GameStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GameRoomServiceTest(
    private val gameRoomService: GameRoomService,
    private val gameRoomRepository: GameRoomRepository
) : DescribeSpec({
    describe("게임방 생성") {
        context("사용자가 게임방을 생성하면") {

            val gameRoom = gameRoomService.createGame()

            it("게임방이 생성된다.") {
                val savedGameRoom = gameRoomRepository.findById(1).get()
                savedGameRoom.gameId shouldBe 1
                savedGameRoom.playerCount shouldBe 1
                savedGameRoom.status shouldBe GameStatus.WAITING
            }
        }
    }

    describe("게임방 참가") {
        val givenGameRoom = gameRoomRepository.save(GameRoom.create(1L))
        context("사용자가 게임방에 참가하면") {
            val joinedGame = gameRoomService.joinGame(1L)

            it("게임방 인원이 증가한다.") {
                joinedGame.playerCount shouldBe 2
            }
        }
    }

    describe("게임방 퇴장") {
        val givenGameRoom = gameRoomRepository.save(GameRoom.create(1L))
        context("사용자가 게임방에서 퇴장하면") {
            val leavedGame = gameRoomService.leaveGame(1L)

            it("게임방 인원이 감소한다.") {
                leavedGame.playerCount shouldBe 0
            }
        }
    }
})