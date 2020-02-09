package ru.otus.hw16.messageServer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw16.messageServer.messagesystem.MessageSystem;
import ru.otus.hw16.messageServer.messagesystem.MsClient;
import ru.otus.hw16.messageServer.messagesystem.MsClientImpl;

@Configuration
@RequiredArgsConstructor
public class MessageSystemConfig {

    @Bean
    @ConfigurationProperties(prefix = "frontend-server")
    public MsClient frontendMsClient(MessageSystem messageSystem) {
        MsClient frontendMsClient = new MsClientImpl(messageSystem);
        return frontendMsClient;
    }

    @Bean
    @ConfigurationProperties(prefix = "db-server")
    public MsClient DBMsClient(MessageSystem messageSystem) {
        MsClient databaseMsClient = new MsClientImpl(messageSystem);
        return databaseMsClient;
    }
}
