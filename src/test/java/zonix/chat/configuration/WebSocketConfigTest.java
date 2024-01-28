package zonix.chat.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WebSocketConfigTest {

    @Test
    public void testConfigureMessageBroker() {
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        MessageBrokerRegistry messageBrokerRegistry = mock(MessageBrokerRegistry.class);

        webSocketConfig.configureMessageBroker(messageBrokerRegistry);

        verify(messageBrokerRegistry).enableSimpleBroker("/topic");
        verify(messageBrokerRegistry).setApplicationDestinationPrefixes("/app");
    }

    @Test
    public void testRegisterStompEndpoints() {
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        StompEndpointRegistry stompEndpointRegistry = mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration registrationMock = mock(StompWebSocketEndpointRegistration.class);

        // Obsługujemy dowolny argument dla addEndpoint()
        when(stompEndpointRegistry.addEndpoint(any())).thenReturn(registrationMock);

        webSocketConfig.registerStompEndpoints(stompEndpointRegistry);

        // Sprawdzamy, czy addEndpoint() zostało wywołane
        verify(stompEndpointRegistry).addEndpoint(any());
        // Sprawdzamy, czy withSockJS() zostało wywołane
        verify(registrationMock).withSockJS();
    }

    @Test
    public void testConfigureWebSocketTransport() {
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        WebSocketTransportRegistration webSocketTransportRegistration = mock(WebSocketTransportRegistration.class);

        webSocketConfig.configureWebSocketTransport(webSocketTransportRegistration);

        verify(webSocketTransportRegistration).setMessageSizeLimit(1024 * 1024 * 10);
        verify(webSocketTransportRegistration).setSendBufferSizeLimit(1024 * 1024 * 10);
        verify(webSocketTransportRegistration).setSendTimeLimit(20000);
    }
}
