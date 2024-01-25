package zonix.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;
import zonix.chat.entity.Message;
import zonix.chat.entity.MessageType;

@Controller
@RequestMapping
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message)
    {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor
    ) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        message.setContent("The user: " + HtmlUtils.htmlEscape(message.getSender()) + " has joined.");
        message.setMessageType(MessageType.JOIN);
        return message;
    }

    @MessageMapping("/chat.leave")
    @SendTo("/topic/public")
    public Message leaveChat(@Payload Message message) {
        message.setContent("The user: " + HtmlUtils.htmlEscape(message.getSender()) + " has left.");
        message.setMessageType(MessageType.LEAVE);
        return message;
    }

}
