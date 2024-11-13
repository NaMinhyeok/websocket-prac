package com.example.webquiz.domain.message

class ChatMessage(
    override val roomId: Long,
    override val type: String = "chat",
    val message: String
) : BaseMessage {
}