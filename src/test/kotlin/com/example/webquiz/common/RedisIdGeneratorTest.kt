package com.example.webquiz.common

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@SpringBootTest
class RedisIdGeneratorTest(
    private val redisIdGenerator: RedisIdGenerator,
    private val redisTemplate: RedisTemplate<String, String>
) : DescribeSpec({

    beforeTest {
        redisTemplate.delete("{gameRoom}:sequence")
    }

    describe("레디스 ID 생성기에서") {
        val key = "gameRoom"
        context("ID를 생성하면") {
            val id = redisIdGenerator.generateGameRoomID(key)

            it("ID가 생성된다.") {
                id shouldBe "1"
            }
        }
        context("Id를 여러번 생성하면") {
            val id1 = redisIdGenerator.generateGameRoomID(key)
            val id2 = redisIdGenerator.generateGameRoomID(key)
            val id3 = redisIdGenerator.generateGameRoomID(key)

            it("ID가 중복되지 않고 순차적으로 생성된다.") {
                id1 shouldBe "1"
                id2 shouldBe "2"
                id3 shouldBe "3"
            }
        }
    }
})