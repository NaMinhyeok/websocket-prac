package com.example.webquiz.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameRoomTest() : DescribeSpec({


    describe("게임방에서") {
        context("게임방을 생성하면") {
            val gameRoom = GameRoom.create(1L)

            it("게임방이 생성된다.") {
                gameRoom.gameId shouldBe 1
                gameRoom.playerCount shouldBe 1
                gameRoom.status shouldBe GameStatus.WAITING
            }
        }

        context("게임방에 참가하면") {
            val gameRoom = GameRoom.create(1L)
            gameRoom.join()

            it("게임방 인원이 증가한다.") {
                gameRoom.playerCount shouldBe 2
            }
        }

        context("게임방에서 퇴장하면") {
            val gameRoom = GameRoom.create(1L)
            gameRoom.leave()

            it("게임방 인원이 감소한다.") {
                gameRoom.playerCount shouldBe 0
            }
        }
    }
})