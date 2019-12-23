package ru.otus.hw15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw15.messagesystem.*;
import ru.otus.hw15.messagesystem.front.FrontendService;
import ru.otus.hw15.messagesystem.front.FrontendServiceImpl;
import ru.otus.hw15.messagesystem.front.handlers.GetCommonUserResponseHandler;

@Configuration
@RequiredArgsConstructor
public class FrontendServiceConfig {

    private final MessageSystem messageSystem;

    @Bean
    public FrontendService frontendService(){

        MsClient frontendMsClient = new MsClientImpl(MsClientName.FRONTEND.getName(), messageSystem);

        FrontendService  frontendService = new FrontendServiceImpl(frontendMsClient, MsClientName.DATABASE.getName());

        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));

        messageSystem.addClient(frontendMsClient);

        return frontendService;
    }
}
