package ru.otus.hw16.messageServer.messagesystem;


import ru.otus.message.Message;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message msg);
}
