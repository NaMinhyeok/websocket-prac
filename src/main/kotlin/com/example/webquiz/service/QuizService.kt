package com.example.webquiz.service

import com.example.webquiz.domain.GameRoomRepository
import com.example.webquiz.domain.QuizRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val gameRoomRepository: GameRoomRepository,
    private val quizMessageService: QuizMessageService
) {

    fun submitAnswer(quizId: Long, answer: Boolean, gameId: Long): Boolean {
        val gameRoom =
            gameRoomRepository.findByIdOrNull(gameId) ?: return throw IllegalArgumentException("게임 룸이 존재하지 않습니다.")
        val quiz = quizRepository.findByIdOrNull(quizId) ?: return throw IllegalArgumentException("퀴즈가 존재하지 않습니다.")
        val isCorrect = quiz.answer == answer
        quizMessageService.publishSubmitAnswer(gameId, quizId, isCorrect)
        return isCorrect;
    }

}