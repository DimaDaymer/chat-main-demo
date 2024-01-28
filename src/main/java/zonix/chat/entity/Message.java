package zonix.chat.entity;

import lombok.*;

/**
 * Klasa encji reprezentująca wiadomość czatu.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    /**
     * Typ wiadomości (np. CHAT, JOIN, LEAVE, IMAGE).
     */
    private MessageType type;

    /**
     * Nadawca wiadomości.
     */
    private String sender;

    /**
     * Treść wiadomości.
     */
    private String content;

    /**
     * Typ zawartości wiadomości (np. tekst, obraz).
     */
    private String contentType;

    /**
     * Ustawia typ wiadomości na podstawie przekazanego ciągu znaków.
     *
     * @param type Ciąg znaków reprezentujący typ wiadomości.
     */
    public void setType(String type) {
        this.type = MessageType.valueOf(type);
    }

    /**
     * Zwraca typ zawartości wiadomości.
     *
     * @return Typ zawartości wiadomości.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Ustawia typ zawartości wiadomości.
     *
     * @param contentType Typ zawartości wiadomości.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
