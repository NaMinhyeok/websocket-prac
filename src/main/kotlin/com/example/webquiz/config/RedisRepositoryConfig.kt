package com.example.webquiz.config

import com.example.webquiz.service.RedisSubscriber
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisRepositoryConfig {

    @Value("\${spring.redis.host}")
    private lateinit var host: String

    @Value("\${spring.redis.port}")
    private var port: Int = 0

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.connectionFactory = redisConnectionFactory()

        val stringRedisSerializer = StringRedisSerializer()
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = stringRedisSerializer

        return redisTemplate
    }

    @Bean
    fun gameTopic(): ChannelTopic {
        return ChannelTopic("game")
    }

    @Bean
    fun redisMessageListenerContainer(
        redisConnectionFactory: RedisConnectionFactory,
        gameMessageListenerAdapter: MessageListenerAdapter,
        chatMessageListenerAdapter: MessageListenerAdapter,
        gameTopic: ChannelTopic,
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        container.addMessageListener(gameMessageListenerAdapter, gameTopic)

        return container
    }

    @Bean
    fun gameMessageListenerAdapter(redisSubscriber: RedisSubscriber): MessageListenerAdapter {
        return MessageListenerAdapter(redisSubscriber, "gameMessage")
    }

}
