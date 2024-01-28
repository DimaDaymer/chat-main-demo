package zonix.chat.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void setType_ValidType_ShouldSetMessageType() {
        // Arrange
        Message message = new Message();

        // Act
        message.setType("CHAT");

        // Assert
        assertEquals(MessageType.CHAT, message.getType());
    }

    @Test
    void getContentType_InitializedContentType_ShouldReturnContentType() {
        // Arrange
        Message message = new Message();
        message.setContentType("text");

        // Act
        String contentType = message.getContentType();

        // Assert
        assertEquals("text", contentType);
    }

    @Test
    void setContentType_ValidContentType_ShouldSetContentType() {
        // Arrange
        Message message = new Message();

        // Act
        message.setContentType("image");

        // Assert
        assertEquals("image", message.getContentType());
    }

    // Add more test cases as needed to cover different scenarios

}
