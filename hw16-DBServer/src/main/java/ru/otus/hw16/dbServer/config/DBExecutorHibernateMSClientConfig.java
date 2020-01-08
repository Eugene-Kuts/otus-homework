package ru.otus.hw16.dbServer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw16.dbServer.client.DBClient;
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

    @Value("${MessageSystem.ClientName.DBService}")
    private String DBServiceClientName;

    @Bean
    public DBClient socketClientForDBServer() {
        return new DBClient();
    }

    @Bean
    public MsClient databaseMsClient() {
        DBServerMsClientImpl dbServerMsClient = new DBServerMsClientImpl(DBServiceClientName, socketClientForDBServer());
        dbServerMsClient.addHandler(MessageType.ADD_USER, new GetAddUserRequestHandler(dbExecutorHibernate));
        dbServerMsClient.addHandler(MessageType.GET_SAVED_USER, new GetAddUserRequestHandler(dbExecutorHibernate));
        return dbServerMsClient;
    }
}
