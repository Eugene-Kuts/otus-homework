package ru.otus.hw16.dbServer.messagesystem;


import ru.otus.message.Message;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message msg);
}
