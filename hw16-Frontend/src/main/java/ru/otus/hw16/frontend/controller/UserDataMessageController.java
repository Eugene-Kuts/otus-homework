package ru.otus.hw16.frontend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.hw16.frontend.messagesystem.front.FrontendService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserDataMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final FrontendService frontendService;

    @MessageMapping("/createUserMessage")
    public void saveUser(String frontMessage) {
        frontendService.createUser(frontMessage, userData -> {
            log.info("DBService ответил сообщением: {}", userData);
            sendFrontMessage(userData);
        });
    }

    private void sendFrontMessage(String frontMessage) {
        simpMessagingTemplate.convertAndSend("/topic/DBServiceResponse", frontMessage);
    }
}
