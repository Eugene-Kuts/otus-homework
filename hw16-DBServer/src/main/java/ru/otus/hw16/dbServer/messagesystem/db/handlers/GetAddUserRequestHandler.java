package ru.otus.hw16.dbServer.messagesystem.db.handlers;

import com.google.gson.Gson;
import ru.otus.hw16.dbServer.db.domain.User;
import ru.otus.hw16.dbServer.db.executor.DBExecutorHibernate;
import ru.otus.message.Message;
import ru.otus.hw16.dbServer.messagesystem.MessageType;
import ru.otus.hw16.dbServer.messagesystem.RequestHandler;
import ru.otus.hw16.dbServer.messagesystem.common.Serializers;

import java.util.Optional;

public class GetAddUserRequestHandler implements RequestHandler {
    private final DBExecutorHibernate<User> dbExecutorHibernate;
    private final Gson gson = new Gson();

    public GetAddUserRequestHandler(DBExecutorHibernate<User> dbExecutorHibernate) {
        this.dbExecutorHibernate = dbExecutorHibernate;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        String jsonUserData = Serializers.deserialize(msg.getPayload(), String.class);
        User newUser = gson.fromJson(jsonUserData, User.class);
        long UserID = dbExecutorHibernate.createWithId(newUser);
        User UserWithID = dbExecutorHibernate.load(UserID);
        String savedUserData = gson.toJson(UserWithID);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(),
                MessageType.ADD_USER.getValue(), Serializers.serialize(savedUserData)));
    }
}
