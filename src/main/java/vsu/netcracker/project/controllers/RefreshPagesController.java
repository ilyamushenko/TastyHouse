package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class RefreshPagesController {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/refresh")
    public void greeting(Map whatHappen) throws Exception {
        System.out.println(whatHappen.get("message"));
        this.messageTemplate.convertAndSend("topic/messages", "refresh all");
    }

}
