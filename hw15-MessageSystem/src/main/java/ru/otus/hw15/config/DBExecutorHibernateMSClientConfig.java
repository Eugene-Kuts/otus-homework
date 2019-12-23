package ru.otus.hw15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw15.db.executor.DBExecutorHibernate;
import ru.otus.hw15.messagesystem.MessageSystem;
import ru.otus.hw15.messagesystem.MessageType;
import ru.otus.hw15.messagesystem.MsClientImpl;
import ru.otus.hw15.messagesystem.MsClientName;
import ru.otus.hw15.messagesystem.db.handlers.GetAddUserRequestHandler;

@Configuration
@RequiredArgsConstructor
public class DBExecutorHibernateMSClientConfig {

    private final DBExecutorHibernate dbExecutorHibernate;
    private final MessageSystem messageSystem;

    @Bean
    public MsClientImpl databaseMsClient(){
        MsClientImpl databaseMsClient = new MsClientImpl(MsClientName.DATABASE.getName(), messageSystem);

        databaseMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbExecutorHibernate));

        messageSystem.addClient(databaseMsClient);

        return databaseMsClient;
    }
}
