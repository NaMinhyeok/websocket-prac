package com.example.webquiz.service

import com.example.webquiz.config.QuizMessageListener
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class QuizSubscribeService(
    private val listenerContainer: RedisMessageListenerContainer,
    private val quizMessageListener: QuizMessageListener
) {
    private val subscribedRooms = ConcurrentHashMap<Long, ChannelTopic>()

    fun subscribe(gameRoomId: Long) {
        val topic = ChannelTopic("quiz_channel:$gameRoomId")
        listenerContainer.addMessageListener(quizMessageListener, topic)
        subscribedRooms[gameRoomId] = topic
    }

}