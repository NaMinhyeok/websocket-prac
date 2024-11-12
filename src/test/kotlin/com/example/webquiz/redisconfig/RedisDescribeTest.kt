package com.example.webquiz.redisconfig

import com.example.webquiz.point.Point
import com.example.webquiz.point.PointRedisRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles("test")
@SpringBootTest
class RedisDescribeTest(
    private val pointRedisRepository: PointRedisRepository
) : DescribeSpec({

    describe("레디스 수정기능 확인") {
        context("포인트 정보 수정시") {
            val id = "minhyeok"
            val refreshTime = LocalDateTime.of(2024, 11, 12, 0, 0)
            val point = Point(id, 100, refreshTime)
            pointRedisRepository.save(point)

            it("값과, 수정 시간을 변경할 수 있다.") {
                val savedPoint = pointRedisRepository.findById(id).get()
                savedPoint.refresh(200, LocalDateTime.of(2024, 12, 13, 0, 0))
                pointRedisRepository.save(savedPoint)

                val refreshPoint = pointRedisRepository.findById(id).get()
                refreshPoint.amount shouldBe 200
                refreshPoint.refreshTime shouldBe LocalDateTime.of(2024, 12, 13, 0, 0)
            }
        }
    }
})
