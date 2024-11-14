package com.example.webquiz.config

import com.ninjasquad.springmockk.MockkBean
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import java.util.concurrent.TimeUnit
import kotlin.test.Test

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketBrokerConfigTest(
) {

    @LocalServerPort
    private var port: Int = 0

    private lateinit var stompClient: WebSocketStompClient
    private lateinit var wsClient: WebSocketClient

    @BeforeEach
    fun setup() {
        wsClient = StandardWebSocketClient()
        stompClient = WebSocketStompClient(wsClient)
        stompClient.messageConverter = MappingJackson2MessageConverter()
    }

    @Test
    fun `웹소켓_연결_테스트_Sock_JS_설정없이`() {
        println("port = ${port}")
        val url = "ws://localhost:${port}/ws"
        val stompSession =
            stompClient.connectAsync(url, object : StompSessionHandlerAdapter() {}).get(60, TimeUnit.SECONDS)

        assertThat(stompSession.isConnected).isTrue()

        stompSession.disconnect()
        stompClient.stop()
    }
}