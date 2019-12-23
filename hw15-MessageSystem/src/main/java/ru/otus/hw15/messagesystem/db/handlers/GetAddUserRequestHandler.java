package ru.otus.hw15.messagesystem.db.handlers;

import com.google.gson.Gson;
import ru.otus.hw15.db.domain.User;
import ru.otus.hw15.db.executor.DBExecutorHibernate;
import ru.otus.hw15.messagesystem.Message;
import ru.otus.hw15.messagesystem.MessageType;
import ru.otus.hw15.messagesystem.RequestHandler;
import ru.otus.hw15.messagesystem.common.Serializers;

import java.util.Optional;

public class GetAddUserRequestHandler implements RequestHandler {
    private final DBExecutorHibernate dbExecutorHibernate;
    private final Gson gson = new Gson();

    public GetAddUserRequestHandler(DBExecutorHibernate dbExecutorHibernate) {
        this.dbExecutorHibernate = dbExecutorHibernate;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        String jsonUserData = Serializers.deserialize(msg.getPayload(), String.class);
        User newUser = gson.fromJson(jsonUserData, User.class);
        long UserID = dbExecutorHibernate.createWithId(newUser);
        User UserWithID = dbExecutorHibernate.load(UserID);
        String savedUserData = gson.toJson(UserWithID);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()),
                MessageType.ADD_USER.getValue(), Serializers.serialize(savedUserData)));
    }
}
