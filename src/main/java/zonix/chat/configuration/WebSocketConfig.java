package zonix.chat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * Klasa konfiguracyjna WebSocket implementująca WebSocketMessageBrokerConfigurer.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Konfiguruje brokera wiadomości do komunikacji WebSocket.
     *
     * @param config Obiekt MessageBrokerRegistry do konfiguracji brokera wiadomości.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * Rejestruje punkty końcowe Stomp do komunikacji WebSocket.
     *
     * @param registry Obiekt StompEndpointRegistry do rejestracji punktów końcowych Stomp.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint").withSockJS();
    }

    /**
     * Konfiguruje ustawienia transportu WebSocket, w tym limity rozmiaru bufora.
     *
     * @param registration Obiekt WebSocketTransportRegistration do konfiguracji ustawień transportu WebSocket.
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(1024 * 1024 * 10);
        registration.setSendBufferSizeLimit(1024 * 1024 * 10);
        registration.setSendTimeLimit(20000);
    }
}
