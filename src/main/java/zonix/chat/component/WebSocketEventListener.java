package zonix.chat.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import zonix.chat.entity.Message;
import zonix.chat.entity.MessageType;

/**
 * Słuchacz zdarzeń WebSocket odpowiedzialny za obsługę zdarzeń połączenia i rozłączenia WebSocket.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    /**
     * Obsługuje zdarzenia połączenia WebSocket.
     *
     * @param connectEvent Zdarzenie połączenia WebSocket.
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent connectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connectEvent.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("Użytkownik dołączył.\n: {}", username);
            var message = Message.builder()
                    .type(MessageType.JOIN)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", message);
        }
    }

    /**
     * Obsługuje zdarzenia rozłączenia WebSocket.
     *
     * @param disconnectEvent Zdarzenie rozłączenia WebSocket.
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("Użytkownik opuścił: {}", username);
            var message = Message.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", message);
        }
    }
}
