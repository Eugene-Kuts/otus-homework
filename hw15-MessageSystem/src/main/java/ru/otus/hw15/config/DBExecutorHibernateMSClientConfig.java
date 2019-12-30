package ru.otus.hw15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw15.db.domain.User;
import ru.otus.hw15.db.executor.DBExecutorHibernate;
import ru.otus.hw15.messagesystem.MessageSystem;
import ru.otus.hw15.messagesystem.MessageType;
import ru.otus.hw15.messagesystem.MsClientImpl;
import ru.otus.hw15.messagesystem.db.handlers.GetAddUserRequestHandler;

@Configuration
@RequiredArgsConstructor
public class DBExecutorHibernateMSClientConfig {

    private final DBExecutorHibernate<User> dbExecutorHibernate;
    private final MessageSystem messageSystem;

    @Value("${MessageSystem.ClientName.DBService}")
    private String DBServiceClientName;

    @Bean
    public MsClientImpl databaseMsClient(){
        MsClientImpl databaseMsClient = new MsClientImpl(DBServiceClientName, messageSystem);

        databaseMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbExecutorHibernate));
        databaseMsClient.addHandler(MessageType.GET_SAVED_USER, new GetAddUserRequestHandler(dbExecutorHibernate));

        messageSystem.addClient(databaseMsClient);

        return databaseMsClient;
    }
}
