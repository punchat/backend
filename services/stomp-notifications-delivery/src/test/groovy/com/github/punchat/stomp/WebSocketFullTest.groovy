package com.github.punchat.stomp

import com.github.punchat.events.MessageReceivedEvent
import com.github.punchat.stomp.notifications.Notifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.lang.Nullable
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import spock.lang.Specification

import java.lang.reflect.Type
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@ActiveProfiles("test")
@SpringBootTest(classes = ComponentTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketFullTest extends Specification {
    @LocalServerPort
    int port

    @Autowired
    Notifier notifier

    CompletableFuture<MessageReceivedEvent> completableEvent = new CompletableFuture<>()

    def 'web sockets test'() {
        given:
        WebSocketClient client = new StandardWebSocketClient()
        WebSocketStompClient stompClient = new WebSocketStompClient(client)
        stompClient.setMessageConverter(new MappingJackson2MessageConverter())
        String url = "ws://localhost:$port/ws"
        ListenableFuture<StompSession> future = stompClient.connect(url, new StompSessionHandlerAdapter() {})
        StompSession session = future.get()
        session.subscribe("/topic/notifications/1", new NotificationHandler())
        MessageReceivedEvent sent = new MessageReceivedEvent(id: 2L, type: MessageReceivedEvent.MessageType.DIRECT)
        notifier.awareUserReceivedMessage(1L, sent)

        when:
        MessageReceivedEvent received = completableEvent.get(2, TimeUnit.SECONDS)

        then:
        received.id == 2L
        received.type == MessageReceivedEvent.MessageType.DIRECT
    }

    class NotificationHandler implements StompFrameHandler {

        @Override
        Type getPayloadType(StompHeaders headers) {
            MessageReceivedEvent
        }

        @Override
        void handleFrame(StompHeaders headers, @Nullable Object payload) {
            MessageReceivedEvent event = payload as MessageReceivedEvent
            completableEvent.complete(event)
        }
    }
}
