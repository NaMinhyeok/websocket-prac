package com.example.webquiz.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameRoomTest() : DescribeSpec({


    describe("게임방에서") {
        context("게임방을 생성하면") {
            val gameRoom = GameRoom()

            it("게임방이 생성된다.") {
                gameRoom.playerCount shouldBe 0
                gameRoom.currentQuizIndex shouldBe 0
                gameRoom.status shouldBe GameStatus.WAITING
            }
        }

        context("게임방에 참가하면") {
            val gameRoom = GameRoom()
            gameRoom.join()

            it("게임방 인원이 증가한다.") {
                gameRoom.playerCount shouldBe 1
            }
        }

        context("게임방에서 퇴장하면") {
            val gameRoom = GameRoom(playerCount = 1)
            gameRoom.leave()

            it("게임방 인원이 감소한다.") {
                gameRoom.playerCount shouldBe 0
            }
        }
    }
})