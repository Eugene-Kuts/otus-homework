package ru.otus.hw15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${MessageSystem.ClientName.FrontEndService}")
    private String FRONTEND_SERVICE_CLIENT_NAME;

    @Value("${MessageSystem.ClientName.DBService}")
    private String DATABASE_SERVICE_CLIENT_NAME;

    @Bean
    public FrontendService frontendService(){

        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);

        FrontendService  frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);

        frontendMsClient.addHandler(MessageType.ADD_USER, new GetCommonUserResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.GET_SAVED_USER, new GetCommonUserResponseHandler(frontendService));

        messageSystem.addClient(frontendMsClient);

        return frontendService;
    }
}
