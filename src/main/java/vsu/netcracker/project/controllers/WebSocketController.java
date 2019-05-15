package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/refresh")
    public void greeting(Map whatHappen) throws Exception {
        String message = (String) whatHappen.get("message");
        switch (message) {
            case "bought new dish":
                this.messageTemplate.convertAndSend("topic/messages", "refresh all");
                break;
            case "change dish status on kitchen":
                this.messageTemplate.convertAndSend("topic/messages/waiter", "refresh");
                break;
        }
    }

}
