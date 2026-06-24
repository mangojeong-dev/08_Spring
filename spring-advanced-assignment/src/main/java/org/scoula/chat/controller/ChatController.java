package org.scoula.chat.controller;

import org.scoula.chat.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @GetMapping("/chat")
    public String chat() {
        return "chat/room";
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage send(
            @DestinationVariable String roomId,
            ChatMessage message) {
        message.setRoomId(roomId);
        message.markSentNow();
        return message;
    }
}
