package com.example.webquiz.redisconfig

import com.example.webquiz.point.Point
import com.example.webquiz.point.PointRedisRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles("test")
@SpringBootTest
class RedisTest(
    private val pointRedisRepository: PointRedisRepository
) : BehaviorSpec({

    afterTest {
        pointRedisRepository.deleteAll()
    }

    Given("데이터를 저장 할 수 있다: point가 존재하고 ") {
        val id = "minhyeok"
        val refreshTime = LocalDateTime.of(2024, 11, 12, 0, 0)
        val point = Point(id, 100, refreshTime)
        When("포인트를 저장하면") {

            pointRedisRepository.save(point)

            Then("저장된 포인트를 찾을 수 있다.") {
                val savedPoint = pointRedisRepository.findById(id).get()
                savedPoint.amount shouldBe 100
                savedPoint.refreshTime shouldBe refreshTime
            }
        }
    }
})
