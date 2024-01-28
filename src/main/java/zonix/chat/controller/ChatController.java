package zonix.chat.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;
import zonix.chat.entity.Message;
import zonix.chat.entity.MessageType;

/**
 * Kontroler obsługujący wiadomości czatu WebSocket.
 */
@Controller
@RequestMapping
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    /**
     * Metoda przetwarzająca obraz i konwertująca go na Base64.
     *
     * @param imageData Dane obrazu w postaci bajtów.
     * @return Obraz zakodowany w formie Base64.
     */
    private String processImageAndConvertToBase64(byte[] imageData) {
        try {
            return Base64.encodeBase64String(imageData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metoda obsługująca wysłanie wiadomości w czacie.
     *
     * @param userDestination
     * @param message         Wiadomość do wysłania.
     * @return Wysłana wiadomość.
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(String userDestination, @Payload Message message) {
        log.info("Wysłana wiadomość: {}", message);
        if (MessageType.IMAGE.equals(message.getType())) {
            try {
                byte[] imageData = Base64.decodeBase64(message.getContent());
                String base64ImageData = processImageAndConvertToBase64(imageData);
                message.setType("IMAGE");
                message.setContent(base64ImageData);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    /**
     * Metoda obsługująca dodanie użytkownika do czatu.
     *
     * @param message         Wiadomość zawierająca informacje o nowym użytkowniku.
     * @param headerAccessor Obiekt dostępu do nagłówków wiadomości.
     * @return Wiadomość powitalna informująca o dołączeniu nowego użytkownika.
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        Message joinMessage = Message.builder()
                .type(MessageType.JOIN)
                .sender(message.getSender())
                .content("Użytkownik " + HtmlUtils.htmlEscape(message.getSender()) + " dołączył.")
                .build();

        return joinMessage;
    }

    /**
     * Metoda obsługująca opuszczenie czatu przez użytkownika.
     *
     * @param message Wiadomość zawierająca informacje o opuszczającym użytkowniku.
     * @return Wiadomość informująca o opuszczeniu czatu.
     */
    @MessageMapping("/chat.leave")
    @SendTo("/topic/public")
    public Message leaveChat(@Payload Message message) {
        Message leaveMessage = Message.builder()
                .type(MessageType.LEAVE)
                .sender(message.getSender())
                .content("Użytkownik " + HtmlUtils.htmlEscape(message.getSender()) + " opuścił czat.")
                .build();

        return leaveMessage;
    }
}
