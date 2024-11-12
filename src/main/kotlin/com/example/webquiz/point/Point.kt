package com.example.webquiz.point

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import java.time.LocalDateTime

@RedisHash("point")
class Point(
    @Id
    val id: String,
    var amount: Long,
    var refreshTime: LocalDateTime
) : Serializable {

    fun refresh(amount: Long, refreshTime: LocalDateTime) {
        if (refreshTime.isAfter(this.refreshTime)) {
            this.amount = amount
            this.refreshTime = refreshTime
        }
    }

    companion object {
        fun create(
            id: String,
            amount: Long,
            refreshTime: LocalDateTime
        ) = Point(
            id = id,
            amount = amount,
            refreshTime = refreshTime
        )
    }

}
