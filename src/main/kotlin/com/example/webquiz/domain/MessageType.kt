package com.example.webquiz.domain

enum class MessageType(
    val description: String
) {
    QUIZ_START("퀴즈 시작"),
    QUIZ_END("퀴즈 종료"),
    SUBMIT_ANSWER("정답 제출"),
    ;
}