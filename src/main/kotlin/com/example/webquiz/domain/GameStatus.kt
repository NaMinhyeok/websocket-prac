package com.example.webquiz.domain

enum class GameStatus(val description: String) {
    WAITING("대기 중"),
    PLAYING("진행 중"),
    FINISHED("종료"),
    ;
}
