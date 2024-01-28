package zonix.chat.entity;

/**
 * Enum reprezentujący różne typy wiadomości czatu.
 */
public enum MessageType {

    /**
     * Typ wiadomości tekstowej.
     */
    CHAT,

    /**
     * Typ wiadomości dołączenia (użytkownik dołączył do czatu).
     */
    JOIN,

    /**
     * Typ wiadomości opuszczania (użytkownik opuścił czat).
     */
    LEAVE,

    /**
     * Typ wiadomości zawierającej obraz.
     */
    IMAGE;
}
