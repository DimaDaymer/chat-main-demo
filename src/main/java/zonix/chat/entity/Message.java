package zonix.chat.entity;

        import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String content;
    private String sender;
    private String receiver;
    private MessageType messageType;
}
