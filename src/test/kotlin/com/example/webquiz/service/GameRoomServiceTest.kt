package com.example.webquiz.service

import com.example.webquiz.domain.GameRoom
import com.example.webquiz.domain.GameRoomRepository
import com.example.webquiz.domain.GameStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class GameRoomServiceTest(
    private val gameRoomService: GameRoomService,
    private val gameRoomRepository: GameRoomRepository
) : DescribeSpec({
    describe("게임방 생성") {
        context("사용자가 게임방을 생성하면") {

            val gameRoom = gameRoomService.createGame()

            it("게임방이 생성된다.") {
                val savedGameRoom = gameRoomRepository.findById("1").get()
                savedGameRoom.id shouldBe "1"
                savedGameRoom.playerCount shouldBe 0
                savedGameRoom.status shouldBe GameStatus.WAITING
            }
        }
    }

    describe("게임방 참가") {
        val givenGameRoom = gameRoomRepository.save(GameRoom(id = "1"))
        context("사용자가 게임방에 참가하면") {
            val joinedGame = gameRoomService.joinGame("1")

            it("게임방 인원이 증가한다.") {
                joinedGame.playerCount shouldBe 1
            }
        }
    }

    describe("게임방 퇴장") {
        val givenGameRoom = gameRoomRepository.save(GameRoom(id = "1", playerCount = 1))
        context("사용자가 게임방에서 퇴장하면") {
            val leavedGame = gameRoomService.leaveGame("1")

            it("게임방 인원이 감소한다.") {
                leavedGame.playerCount shouldBe 0
            }
        }
    }
})