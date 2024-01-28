package zonix.chat.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import zonix.chat.entity.Message;
import zonix.chat.entity.MessageType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ChatControllerTest {

    @Test
    public void testAddUser() {
        ChatController chatController = new ChatController();
        SimpMessageHeaderAccessor headerAccessor = mock(SimpMessageHeaderAccessor.class);

        Message joinMessage = chatController.addUser(
                Message.builder()
                        .type(MessageType.JOIN)
                        .sender("testUser")
                        .content("User joined.")
                        .build(),
                headerAccessor);

        assertEquals(MessageType.JOIN, joinMessage.getType());
        assertEquals("testUser", joinMessage.getSender());
        assertEquals("Użytkownik testUser dołączył.", joinMessage.getContent());
    }

    @Test
    public void testLeaveChat() {
        ChatController chatController = new ChatController();

        Message leaveMessage = chatController.leaveChat(
                Message.builder()
                        .type(MessageType.LEAVE)
                        .sender("testUser")
                        .content("User left.")
                        .build());

        assertEquals(MessageType.LEAVE, leaveMessage.getType());
        assertEquals("testUser", leaveMessage.getSender());
        assertEquals("Użytkownik testUser opuścił czat.", leaveMessage.getContent());
    }

    // Add more tests as needed for other methods in ChatController

}
