package ru.otus.hw16.dbServer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw16.dbServer.client.DBSocketClient;
import ru.otus.hw16.dbServer.db.domain.User;
import ru.otus.hw16.dbServer.db.executor.DBExecutorHibernate;
import ru.otus.hw16.dbServer.messagesystem.DBServerMsClientImpl;
import ru.otus.hw16.dbServer.messagesystem.MessageType;
import ru.otus.hw16.dbServer.messagesystem.MsClient;
import ru.otus.hw16.dbServer.messagesystem.db.handlers.GetAddUserRequestHandler;

@Configuration
@RequiredArgsConstructor
public class DBExecutorHibernateMSClientConfig {

    private final DBExecutorHibernate<User> dbExecutorHibernate;

    private final DBSocketClient dbSocketClient;

    @Value("${message-system.client-name.DBService}")
    private String DBServiceClientName;

    @Bean
    public MsClient databaseMsClient() {
        DBServerMsClientImpl dbServerMsClient = new DBServerMsClientImpl(DBServiceClientName, dbSocketClient);
        dbServerMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbExecutorHibernate));
        dbServerMsClient.addHandler(MessageType.GET_SAVED_USER, new GetAddUserRequestHandler(dbExecutorHibernate));
        return dbServerMsClient;
    }
}
