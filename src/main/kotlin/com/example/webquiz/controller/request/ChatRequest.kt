package com.example.webquiz.controller.request

class ChatRequest(
    val roomId: Long,
    val sender: String,
    val message: String
) {
}