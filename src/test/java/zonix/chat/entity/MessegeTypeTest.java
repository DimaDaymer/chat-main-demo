package zonix.chat.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTypeTest {

    @Test
    void valueOf_ValidTypeName_ShouldReturnCorrectEnum() {
        // Arrange
        String validTypeName = "CHAT";

        // Act
        MessageType messageType = MessageType.valueOf(validTypeName);

        // Assert
        assertEquals(MessageType.CHAT, messageType);
    }

    @Test
    void values_ShouldReturnAllEnumValues() {
        // Act
        MessageType[] values = MessageType.values();

        // Assert
        assertEquals(4, values.length);
        assertArrayEquals(new MessageType[]{MessageType.CHAT, MessageType.JOIN, MessageType.LEAVE, MessageType.IMAGE}, values);
    }

    // Add more test cases as needed to cover different scenarios

}
