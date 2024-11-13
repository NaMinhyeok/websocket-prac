package com.example.webquiz.domain.message

sealed interface BaseMessage {
    val roomId: Long
    val type: String
}